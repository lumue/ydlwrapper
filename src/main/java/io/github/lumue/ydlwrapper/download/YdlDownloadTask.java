package io.github.lumue.ydlwrapper.download;

import io.github.lumue.ydlwrapper.metadata.NewDownloadStatusMessage;
import io.github.lumue.ydlwrapper.metadata.YdlDownloadMetadataAccessor;
import io.github.lumue.ydlwrapper.metadata.YdlStatusMessage;
import io.github.lumue.ydlwrapper.metadata.single_info_json.SingleInfoJsonDownloadMetadataAccessor;
import io.github.lumue.ydlwrapper.shared.StreamScanner;
import io.github.lumue.ydlwrapper.metadata.single_info_json.YdlInfoJson;
import io.github.lumue.ydlwrapper.metadata.single_info_json.YdlInfoJsonParser;
import io.github.lumue.ydlwrapper.shared.YoutubeDlExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static io.github.lumue.ydlwrapper.shared.YoutubeDlExecutor.Option.*;

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
	private final YdlInfoJsonParser infoJsonParser=new YdlInfoJsonParser();
	private final boolean writeInfoJson;
	private final AtomicReference<YdlInfoJson> ydlDownloadTaskMetadata=new AtomicReference<>(null);
	private YdlDownloadMetadataAccessor ydlDownloadMetadataAccessor;

	public enum YdlDownloadState{EXECUTING, ERROR, SUCCESS, PENDING}

	private final String url;

	private final File outputFolder;

	private final AtomicReference<YdlDownloadState> downloadState=new AtomicReference<>(YdlDownloadState.PENDING);
	private final AtomicReference<YdlFileDownload> currentDownload=new AtomicReference<>(null);
	private final YdlCallback<YdlDownloadState> onStateChanged;
	private final YdlCallback<YdlStatusMessage> onStdout;
	private final YdlCallback<YdlStatusMessage> onStderr;
	private final YdlCallback<YdlFileDownload> onNewOutputFile;

	private final ConcurrentLinkedDeque<YdlFileDownload> fileDownloads =new ConcurrentLinkedDeque<>();

	YdlDownloadTask(
			String pathToYdl,
			String url,
			String outputFolder,
			YdlCallback<YdlDownloadState> onStateChanged,
			YdlCallback<YdlStatusMessage> onStdout,
			YdlCallback<YdlStatusMessage> onStderr,
			YdlCallback<YdlFileDownload> onNewOutputFile,
			boolean writeInfoJson) {
		this.pathToYdl = Objects.requireNonNull(pathToYdl);
		this.onStateChanged = Objects.requireNonNull(onStateChanged);
		this.onStdout = Objects.requireNonNull(onStdout);
		this.onStderr = Objects.requireNonNull(onStderr);
		this.onNewOutputFile = Objects.requireNonNull(onNewOutputFile);
		this.url = Objects.requireNonNull(url,"url must not be null");
		this.outputFolder = new File(Objects.requireNonNull(outputFolder));
		this.writeInfoJson=writeInfoJson;
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
			YoutubeDlExecutor.Builder builder = YoutubeDlExecutor.newBuilder(templateExecutor)
					.withStdoutConsumer(new StreamScanner(this::onStdout))
					.withStderrConsumer(new StreamScanner(this::onStderr));

			if(this.writeInfoJson)
				builder.withOptions(WRITE_INFO_JSON);

			result= builder
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
		prepared.getAndSet(false);
		fileDownloads.clear();
		try {
			int result = YoutubeDlExecutor.newBuilder(templateExecutor)
					.withOptions(DUMP_SINGLE_JSON)
					.withStdoutConsumer(inputStream -> {
						YdlInfoJson ydlInfoJson = infoJsonParser.apply(inputStream);
						ydlDownloadTaskMetadata.getAndSet(ydlInfoJson);
					})
					.build()
					.execute();
			prepared.getAndSet(result==0);
			this.ydlDownloadMetadataAccessor =new SingleInfoJsonDownloadMetadataAccessor(ydlDownloadTaskMetadata.get());
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
			this.onStderr.handleCallback(this, YdlStatusMessage.createYdlStatusMessage(s));
	}


	private void onStdout(String nextLine) {
		LOGGER.debug("youtube-dl wrote to stdout: "+nextLine);

		YdlStatusMessage message= YdlStatusMessage.createYdlStatusMessage(nextLine);

		if(message instanceof NewDownloadStatusMessage){
			NewDownloadStatusMessage downloadStatusMessage = (NewDownloadStatusMessage) message;
			onNewDownloadFile(downloadStatusMessage);
			this.onNewOutputFile.handleCallback(this,this.currentDownload.get());
		}


		if(this.onStdout!=null)
			this.onStdout.handleCallback(this, message);
	}

	private void onNewDownloadFile(NewDownloadStatusMessage message) {
		String extension = message.parseExtension();
		String filename = message.parseFilename();
		String formatId = message.parseFormatId();
		Long filesize = ydlDownloadMetadataAccessor.getFilesize(filename, formatId).orElse(0L);
		YdlFileDownload download = YdlFileDownload.builder()
				.setExtension(extension)
				.setFilename(filename)
				.setExpectedSize(filesize)
				.setFormat(formatId)
				.createYdlFileDownload();
		this.currentDownload.getAndSet(download);
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
		private YdlCallback<YdlFileDownload> onNewOutputFile=(a,b)->{};
		private String outputFolder=".";

		private YdlCallback<YdlDownloadState> onStateChanged=(a,b)->{};
		private boolean writeInfoJson;

		public YdlDownloadTask build() {
			return new YdlDownloadTask(pathToYdl, url, outputFolder,  onStateChanged, onStdout, onStderr, onNewOutputFile,writeInfoJson );
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

		public YdlDownloadTaskBuilder onNewOutputFile(YdlCallback<YdlFileDownload> onNewOutputFile) {
			this.onNewOutputFile = onNewOutputFile;
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

		public YdlDownloadTaskBuilder setWriteInfoJson(boolean writeInfoJson) {
			this.writeInfoJson = writeInfoJson;
			return this;
		}
	}


	@FunctionalInterface
	public interface  YdlCallback<T> {
		void handleCallback(YdlDownloadTask task, T object);
	}
}
