package io.github.lumue.getdown.core.download.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

/**
 * Takes care of jobs that should be executed.
 * jobs are added to a queue, and then taken one by one and passed to
 * the prepare and download executors.
 */
public class AsyncDownloadJobRunner implements Runnable {
	
	
	private final ThreadPoolTaskExecutor downloadExecutor;
	
	private final ThreadPoolTaskExecutor prepareExecutor;
	
	private final ThreadPoolTaskExecutor postprocessExecutor;
	
	private final AtomicBoolean shouldRun = new AtomicBoolean(false);
	
	
	private final List<DownloadJob> running = new ArrayList<>();
	
	private final List<DownloadJob> done = new ArrayList<>();
	
	//queue capacity can be small.. running a job should be virtually nonblocking, since prepare an download are implemented as async operations
	private final BlockingQueue<DownloadJob> jobQueue = new PriorityBlockingQueue<>(10, new Comparator<DownloadJob>() {
		@Override
		public int compare(DownloadJob o1, DownloadJob o2) {
			return o1.getIndex().compareTo(o2.getIndex());
		}
	});
	
	
	private static Logger LOGGER = LoggerFactory.getLogger(AsyncDownloadJobRunner.class);
	private final Executor jobRunner;
	
	
	public AsyncDownloadJobRunner(
			int maxThreadsPrepare,
			int maxThreadsDownload,
			int maxThreadsPostprocess) {
		super();
		this.downloadExecutor = executor("download-executor",maxThreadsDownload);
		this.prepareExecutor = executor("prepare-executor",maxThreadsPrepare);
		this.postprocessExecutor = executor("postprocess-executor",maxThreadsPostprocess);
		this.jobRunner = executor("jobrunner-executor",1);
	}
	
	
	private void runJob(final DownloadJob job) {
		String jobUrl = job.getUrl();
		AsyncDownloadJobRunner.LOGGER.debug("starting " + jobUrl);
		running.add(job);
		if (!job.isPrepared()) {
			CompletableFuture.runAsync(job::prepare, prepareExecutor)
					.thenRunAsync(job::executeDownload, downloadExecutor)
					.thenRunAsync(job::postProcess, postprocessExecutor)
					.thenRun(() -> {
						running.remove(job);
						done.add(job);
					});
		} else
			CompletableFuture.runAsync(job::executeDownload, downloadExecutor)
					.thenRunAsync(job::postProcess, postprocessExecutor)
					.thenRun(() -> {
						running.remove(job);
						done.add(job);
					});
	}
	
	public synchronized void  submitJob(final DownloadJob job) {
		boolean alreadySubmitted = isAlreadySubmitted(job);
		
		if (alreadySubmitted) {
			LOGGER.warn(job.getUrl() + " already submitted. wont queue ");
			return;
		}
		
		String jobUrl = job.getUrl();
		job.waiting();
		AsyncDownloadJobRunner.LOGGER.debug("queueing " + jobUrl + " for execution");
		jobQueue.add(job);
	}
	
	private boolean isAlreadySubmitted(DownloadJob job) {
		boolean alreadySubmitted = false;
		final String handle = job.getDownloadTask().getHandle();
		for (DownloadJob j : running) {
			if (j.getDownloadTask().getHandle().equals(handle)) {
				alreadySubmitted = true;
				break;
			}
		}
		if(!alreadySubmitted)
			for (DownloadJob j : jobQueue) {
				if (j.getDownloadTask().getHandle().equals(handle)) {
					alreadySubmitted = true;
					break;
				}
			}
		return alreadySubmitted;
	}
	
	
	public void cancelJob(DownloadJob job) {
		job.cancel();
	}
	
	
	private static ThreadPoolTaskExecutor executor(String name,Integer threads) {
		final ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
		threadPoolTaskExecutor.setThreadNamePrefix(name);
		threadPoolTaskExecutor.setThreadGroupName(name);
		threadPoolTaskExecutor.setCorePoolSize(threads);
		threadPoolTaskExecutor.setMaxPoolSize(threads);
		threadPoolTaskExecutor.initialize();
		return threadPoolTaskExecutor;
	}
	
	@Override
	public void run() {
		while (shouldRun.get()) {
			try {
				runJob(jobQueue.take());
			} catch (InterruptedException e) {
				LOGGER.warn("interrupted while accessing job queue", e);
			}
		}
	}
	
	public void stop() {
		shouldRun.compareAndSet(true, false);
	}
	
	
	public Stream<DownloadJob> streamRunning() {
		return running.stream();
	}
	
	
	public Stream<DownloadJob> streamQueued() {
		return jobQueue.stream();
	}
	
	public Stream<DownloadJob> streamDone() {
		return done.stream();
	}
	
	@PostConstruct
	public void start() {
		shouldRun.compareAndSet(false, true);
		jobRunner.execute(this);
	}
}
