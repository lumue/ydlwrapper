package io.github.lumue.ydlwrapper.download;

import io.github.lumue.ydlwrapper.shared.StreamScanner;
import io.github.lumue.ydlwrapper.metadata.YdlDownloadTaskMetadata;
import io.github.lumue.ydlwrapper.metadata.YdlDownloadTaskMetadataParser;
import io.github.lumue.ydlwrapper.shared.YoutubeDlExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static io.github.lumue.ydlwrapper.shared.YoutubeDlExecutor.Option.DUMP_SINGLE_JSON;
import static io.github.lumue.ydlwrapper.shared.YoutubeDlExecutor.Option.NEW_LINE;
import static io.github.lumue.ydlwrapper.shared.YoutubeDlExecutor.Option.NO_COLOR;

/**
 * Execute youtube-dl download
 *
 * Created by lm on 06.03.16.
 */
public class YdlDownloadTask {

	private final YoutubeDlExecutor templateExecutor;
	private final Logger LOGGER= LoggerFactory.getLogger(YdlDownloadTask.class);

	private final String pathToYdl;
	private final AtomicBoolean prepared=new AtomicBoolean(false);
	private final YdlDownloadTaskMetadataParser infoJsonParser=new YdlDownloadTaskMetadataParser();
	private YdlDownloadTaskMetadata ydlDownloadTaskMetadata;

	public enum YdlDownloadState{EXECUTING, ERROR, SUCCESS, PENDING}

	private final String url;

	private final File outputFolder;

	private final AtomicReference<YdlDownloadState> downloadState=new AtomicReference<>(YdlDownloadState.PENDING);

	private final YdlCallback<YdlDownloadState> onStateChanged;
	private final YdlCallback<YdlStatusMessage> onStdout;
	private final YdlCallback<YdlStatusMessage> onStderr;
	private final YdlCallback<File> onNewOutputFile;

	private final Map<String,YdlFileDownload> fileDownloadMap=new ConcurrentHashMap<>();

	YdlDownloadTask(
			String pathToYdl,
			String url,
			String outputFolder,
			YdlCallback<YdlDownloadState> onStateChanged,
			YdlCallback<YdlStatusMessage> onStdout,
			YdlCallback<YdlStatusMessage> onStderr,
			YdlCallback<File> onNewOutputFile) {
		this.pathToYdl = Objects.requireNonNull(pathToYdl);
		this.onStateChanged = Objects.requireNonNull(onStateChanged);
		this.onStdout = Objects.requireNonNull(onStdout);
		this.onStderr = Objects.requireNonNull(onStderr);
		this.onNewOutputFile = Objects.requireNonNull(onNewOutputFile);
		this.url = Objects.requireNonNull(url,"url must not be null");
		this.outputFolder = new File(Objects.requireNonNull(outputFolder));
		templateExecutor = YoutubeDlExecutor.newBuilder()
				.withYdlLocation(pathToYdl)
				.withUrl(getUrl())
				.withOutputFolder(this.outputFolder)
				.withOptions(NEW_LINE,NO_COLOR)
				.build();
	}



	public String getUrl() {
		return url;
	}


	public synchronized void execute()  {

		LOGGER.info("starting download from url "+getUrl()+" to path "+outputFolder.getAbsolutePath());

		if(!isPrepared())
			prepare();

		if(!downloadState.compareAndSet(YdlDownloadState.PENDING,YdlDownloadState.EXECUTING)){
			throw new YdlDownloadError.IllegalDownloadState("can not start download with download-state:"+getDownloadState());
		}
		onStateChanged();

		int result=-1;
		try {
			result= YoutubeDlExecutor.newBuilder(templateExecutor)
					.withStdoutConsumer(new StreamScanner(s -> onStdout(s)))
					.withStderrConsumer(new StreamScanner(s -> onStderr(s)))
					.build()
					.execute();

			YdlDownloadState exitDownloadState=result==0?
					YdlDownloadState.SUCCESS
					:YdlDownloadState.ERROR;
			downloadState.compareAndSet(YdlDownloadState.EXECUTING,exitDownloadState);
		} catch (Exception e) {
			downloadState.compareAndSet(YdlDownloadState.EXECUTING,YdlDownloadState.ERROR);
		}
		LOGGER.info("finished download from url "+getUrl()+" to path "+outputFolder.getAbsolutePath()+" with success code "+result);
	}

	public synchronized void prepare()  {
		prepared.set(false);
		fileDownloadMap.clear();
		try {
			int result = YoutubeDlExecutor.newBuilder(templateExecutor)
					.withOptions(DUMP_SINGLE_JSON)
					.withStdoutConsumer(inputStream -> ydlDownloadTaskMetadata=infoJsonParser.apply(inputStream))
					.build()
					.execute();
			prepared.set(result==0);
		} catch (Exception e) {
			throw new RuntimeException("error getting metadata",e);
		}

	}

	private boolean isPrepared() {
		return prepared.get();
	}

	private void onStderr(String s) {
		LOGGER.warn("youtube-dl wrote to stderr: "+s);
		if(this.onStderr!=null)
			this.onStderr.handleCallback(this,new YdlStatusMessage(s));
	}


	private void onStdout(String nextLine) {
		LOGGER.debug("youtube-dl wrote to stdout: "+nextLine);

		YdlStatusMessage message=new YdlStatusMessage(nextLine);

		if(message.isNewOutputFileSignal())
			this.onNewOutputFile.handleCallback(this,new File(outputFolder.getAbsolutePath()+message.parseFilename()));

		if(this.onStdout!=null)
			this.onStdout.handleCallback(this, message);
	}

	private void onStateChanged() {
		if(onStateChanged!=null)
			onStateChanged.handleCallback(this,downloadState.get());
	}


	public static YdlDownloadTaskBuilder builder(){
		return new YdlDownloadTaskBuilder();
	}

	public YdlDownloadState getDownloadState() {
		return downloadState.get();
	}

	public static class YdlDownloadTaskBuilder {

		private String pathToYdl="/usr/local/bin/youtube-dl";
		private String url;
		private YdlCallback<YdlStatusMessage> onStdout=(a,b)->{};
		private YdlCallback<YdlStatusMessage> onStderr=(a,b)->{};
		private YdlCallback<File> onNewOutputFile=(a,b)->{};
		private String outputFolder=".";
		private ExecutorService executorService;

		private YdlCallback<YdlDownloadState> onStateChanged=(a,b)->{};

		public YdlDownloadTask build() {
			return new YdlDownloadTask(pathToYdl, url, outputFolder,  onStateChanged, onStdout, onStderr, onNewOutputFile);
		}

		public YdlDownloadTaskBuilder setPathToYdl(String pathToYdl) {
			this.pathToYdl = pathToYdl;
			return this;
		}

		public YdlDownloadTaskBuilder onStdout(YdlCallback<YdlStatusMessage> onStdout) {
			this.onStdout = onStdout;
			return this;
		}

		public YdlDownloadTaskBuilder onStderr(YdlCallback<YdlStatusMessage> onStderr) {
			this.onStderr = onStderr;
			return this;
		}

		public YdlDownloadTaskBuilder onNewOutputFile(YdlCallback<File> onNewOutputFile) {
			this.onNewOutputFile = onNewOutputFile;
			return this;
		}

		public YdlDownloadTaskBuilder setExecutorService(ExecutorService executorService) {
			this.executorService = executorService;
			return this;
		}

		public YdlDownloadTaskBuilder setUrl(String url) {
			this.url = url;
			return this;
		}

		public YdlDownloadTaskBuilder setOutputFolder(String outputFolder) {
			this.outputFolder = outputFolder;
			return this;
		}

		public void setOnStateChanged(YdlCallback<YdlDownloadState> onStateChanged) {
			this.onStateChanged = onStateChanged;
		}
	}


	@FunctionalInterface
	public interface  YdlCallback<T> {
		void handleCallback(YdlDownloadTask task, T object);
	}
}
