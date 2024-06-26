package io.github.lumue.getdown.core.download;

import io.github.lumue.getdown.core.common.util.Observer;
import io.github.lumue.getdown.core.download.files.WorkPathManager;
import io.github.lumue.getdown.core.download.job.*;
import io.github.lumue.getdown.core.download.job.AsyncValidateTaskJobRunner;
import io.github.lumue.getdown.core.download.task.DownloadTask;
import io.github.lumue.getdown.core.download.task.DownloadTaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.bus.Event;
import reactor.bus.EventBus;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.stream.Stream;

import static io.github.lumue.getdown.core.download.job.DownloadJob.DownloadJobState.RUNNING;

/**
 * manage the execution of downloads
 * 
 * @author lm
 *
 */
public class DownloadService {

	private final static Logger LOGGER=LoggerFactory.getLogger(DownloadService.class);

	private final AsyncDownloadJobRunner downloadJobRunner;

	private final String downloadPath;

	private final EventBus eventbus;

	private final UrlProcessor urlProcessor;

	private final DownloadTaskRepository downloadTaskRepository;

	private final WorkPathManager workPathManager;
	
	private final AsyncValidateTaskJobRunner validateTaskRunner;

	private final Collection<DownloadJobFactory> downloadJobFactories;
	private final Collection<ValidateTaskJobFactory> validateJobFactories;



	public DownloadService(DownloadTaskRepository downloadTaskRepository,
                           AsyncDownloadJobRunner downloadJobRunner,
                           AsyncValidateTaskJobRunner validateTaskRunner,
                           String downloadPath,
                           EventBus eventbus,
                           UrlProcessor urlProcessor,
                           WorkPathManager workPathManager,
                           Collection<DownloadJobFactory> downloadJobBuilders,
						   Collection<ValidateTaskJobFactory> validateJobFactories) {
		super();
		this.downloadTaskRepository=downloadTaskRepository;
		this.downloadJobRunner = downloadJobRunner;
		this.downloadPath = downloadPath;
		this.eventbus = eventbus;
		this.urlProcessor = urlProcessor;
		this.workPathManager = workPathManager;
		this.validateTaskRunner=validateTaskRunner;
        this.downloadJobFactories = new ArrayList<>(downloadJobBuilders);
        this.validateJobFactories = new ArrayList<>(validateJobFactories);
    }



	public DownloadTask addDownloadTask(final String url) {
		String processedUrl=preprocessUrl(url);

		DownloadTask.DownloadTaskBuilder builder = DownloadTask
				.builder()
				.withSourceUrl(processedUrl)
				.withTargetLocation("");
		final DownloadTask downloadTask = createDownloadTask(builder);
		validateTask(downloadTask);
		return downloadTask;
	}
	
	public void validateTask(DownloadTask task){

		ValidateTaskJob job =  validateJobFactories.stream()
				.findFirst()
				.orElseThrow()
				.create(task);

		job.addObserver((Observer<ValidateTaskJob>) observable -> {
			eventbus.notify("tasks-state", Event.wrap(Objects.requireNonNull(observable.getTask())));
			downloadTaskRepository.update(observable.getTask());
		});

		validateTaskRunner.submitJob(job);
	}
	


	public DownloadTask removeDownloadTask(final DownloadTask task) {
        LOGGER.info("removing task {}", task);
		downloadTaskRepository.remove(task.getHandle());
		eventbus.notify("tasks-removed", Event.wrap(Objects.requireNonNull(task)));
		return task;
	}

	private DownloadTask createDownloadTask(DownloadTask.DownloadTaskBuilder builder) {
		DownloadTask task = downloadTaskRepository.create(builder);
		try {
			workPathManager.createPath(task.getHandle());
		} catch (IOException e) {
			downloadTaskRepository.remove(task.getHandle());
			throw new RuntimeException(e);
		}
		eventbus.notify("tasks-created", Event.wrap(Objects.requireNonNull(task)));
        LOGGER.info("task {} created", task);
		return task;
	}

	private String preprocessUrl(String url) {
		return urlProcessor.processUrl(url);
	}


	public DownloadJob startDownload(final String handle) {
		DownloadTask downloadTask = downloadTaskRepository.get(handle);
		
		DownloadJob job =  downloadJobFactories.stream()
				.findFirst()
				.orElseThrow()
						.create(downloadTask);

		job.addObserver(o ->
				eventbus.notify("downloads-progress", Event.wrap(Objects.requireNonNull(o))
				));
		
		downloadJobRunner.submitJob(job);
		
		eventbus.notify("downloads", Event.wrap(Objects.requireNonNull(job)));
		
		return job;
	}


	public void cancelDownload(final String handle){
		DownloadJob job = getDownload(handle);
		if(job==null){
            LOGGER.warn("no job with handle {} found. nothing to cancel", handle);
			return;
		}

		downloadJobRunner.cancelJob(job);
	}

	public void removeDownload(String downloadJobHandle) {
		DownloadJob downloadJob = getDownload(downloadJobHandle);
		if(downloadJob==null){
            LOGGER.warn("no job with handle {} found. nothing to remove", downloadJobHandle);
			return;
		}

        LOGGER.debug("removing download {}", downloadJob);
		if(RUNNING.equals(downloadJob.getState())){
			cancelDownload(downloadJobHandle);
		}
		
		removeDownloadTask(downloadJob.getDownloadTask());
	}



	public void restartDownload(String downloadJobHandle) {
		DownloadJob downloadJob = getDownload(downloadJobHandle);
		if(RUNNING.equals(downloadJob.getState())){
			cancelDownload(downloadJobHandle);
		}
		startDownload(downloadJobHandle);
	}



	public Stream<DownloadJob> streamDownloads(){
			return Stream.concat(
					downloadJobRunner.streamQueued(),
					Stream.concat(downloadJobRunner.streamRunning(),downloadJobRunner.streamDone())
			);
	}

	public DownloadJob getDownload(String downloadJobHandle) {
		return this.streamDownloads()
				.filter(downloadJob -> downloadJob.getHandle().equals(downloadJobHandle))
				.findFirst()
				.orElse(null);
	}



	public Stream<DownloadJob> streamRunningDownloads() {
		return this.downloadJobRunner.streamRunning();
	}

	public Stream<DownloadJob> streamWaitingDownloads() {
		return this.downloadJobRunner.streamQueued();
	}



	@PostConstruct
	public void init() throws IOException {
		if(!Files.exists(Paths.get(this.downloadPath)))
			Files.createDirectory(Paths.get(this.downloadPath));
	}

	public void removeAll() {
		LOGGER.debug("removing all downloads");
		this.streamDownloads().forEach(job->removeDownload(job.getHandle()));
	}

}
