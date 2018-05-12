package io.github.lumue.ydlwrapper.download;

import io.github.lumue.ydlwrapper.metadata.CurrentFilesizeMetadataAccessor;
import io.github.lumue.ydlwrapper.metadata.filesystem.FilesystemCurrentFilesizeAccessor;
import io.github.lumue.ydlwrapper.metadata.statusmessage.NewDownloadStatusMessage;
import io.github.lumue.ydlwrapper.metadata.statusmessage.ProgressStatusMessage;
import io.github.lumue.ydlwrapper.metadata.statusmessage.YdlStatusMessage;
import io.github.lumue.ydlwrapper.metadata.single_info_json.SingleInfoJsonMetadataAccessor;
import io.github.lumue.ydlwrapper.shared.StreamScanner;
import io.github.lumue.ydlwrapper.metadata.single_info_json.YdlInfoJson;
import io.github.lumue.ydlwrapper.metadata.single_info_json.YdlInfoJsonParser;
import io.github.lumue.ydlwrapper.shared.YoutubeDlExecutor;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import static io.github.lumue.ydlwrapper.shared.YoutubeDlExecutor.Option.*;
import static java.util.Objects.requireNonNull;

/**
 * Execute youtube-dl download
 * <p>
 * Created by lm on 06.03.16.
 */
@SuppressWarnings("WeakerAccess")
public class YdlDownloadTask {

	private final YoutubeDlExecutor templateExecutor;

	private final AtomicReference<Future<Integer>> executionFuture = new AtomicReference<>();

	private final Logger LOGGER = LoggerFactory.getLogger(YdlDownloadTask.class);

	private final AtomicBoolean prepared = new AtomicBoolean(false);
	private final YdlInfoJsonParser infoJsonParser = new YdlInfoJsonParser();
	private final boolean writeInfoJson;

	private final AtomicReference<String> infoJsonAsText=new AtomicReference<>(null);
	private final AtomicReference<YdlInfoJson> ydlDownloadTaskMetadata = new AtomicReference<>(null);
	private SingleInfoJsonMetadataAccessor singleInfoJsonMetadataAccessor;
	private final CurrentFilesizeMetadataAccessor currentFilesizeMetadataAccessor = new FilesystemCurrentFilesizeAccessor();

	public void executeAsync() {
		prepareAndDownload();
	}


	public enum YdlDownloadState {EXECUTING, ERROR, SUCCESS, PENDING, CANCELED}

	private final String url;


	private final File outputFolder;

	private final AtomicReference<YdlDownloadState> downloadState = new AtomicReference<>(YdlDownloadState.PENDING);
	private final AtomicReference<YdlFileDownload> currentDownload = new AtomicReference<>(null);
	private final YdlCallback<YdlDownloadState> onStateChanged;
	private final YdlCallback<SingleInfoJsonMetadataAccessor> onPrepared;
	private final YdlCallback<YdlStatusMessage> onStdout;
	private final YdlCallback<YdlStatusMessage> onStderr;
	private final YdlCallback<YdlFileDownload> onNewOutputFile;
	private final YdlCallback<YdlFileDownload> onOutputFileChange;
	private final YdlCallback<YdlFileDownload> onCancel;


	YdlDownloadTask(
			String pathToYdl,
			String url,
			String outputFolder,
			boolean forceMp4, YdlCallback<YdlDownloadState> onStateChanged,
			YdlCallback<YdlStatusMessage> onStdout,
			YdlCallback<YdlStatusMessage> onStderr,
			YdlCallback<YdlFileDownload> onNewOutputFile,
			YdlCallback<YdlFileDownload> onOutputFileChange,
			YdlCallback<SingleInfoJsonMetadataAccessor> onPrepared,
			boolean writeInfoJson,
			YdlCallback<YdlFileDownload> onCancel) {
		this.onOutputFileChange = onOutputFileChange;
		this.onPrepared = onPrepared;
		this.onStateChanged = requireNonNull(onStateChanged);
		this.onStdout = requireNonNull(onStdout);
		this.onStderr = requireNonNull(onStderr);
		this.onNewOutputFile = requireNonNull(onNewOutputFile);
		this.url = requireNonNull(url, "url must not be null");
		this.outputFolder = new File(requireNonNull(outputFolder));
		this.writeInfoJson = writeInfoJson;
		this.onCancel = onCancel;
		YoutubeDlExecutor.Builder builder = YoutubeDlExecutor.newBuilder()
				.withYdlLocation(requireNonNull(pathToYdl))
				.withUrl(getUrl())
				.withOutputFolder(this.outputFolder)
				.withOptions(NEW_LINE, NO_COLOR);
		if (forceMp4)
			builder.withOptions(FORCE_MP4);
		templateExecutor = builder
				.build();
	}


	public Optional<YdlInfoJson> getYdlDownloadTaskMetadata() {
		return Optional.ofNullable(ydlDownloadTaskMetadata.get());
	}
	
	public Optional<String> getYdlInfoJsonAsText() {
		return Optional.ofNullable(infoJsonAsText.get());
	}

	public String getUrl() {
		return url;
	}

	public YdlFileDownload getCurrentDownload() {
		return currentDownload.get();
	}

	public synchronized void cancel() {

		LOGGER.info("cancelling download from url " + getUrl() + " to path " + outputFolder.getAbsolutePath());
		if (executionFuture.get() == null || !downloadState.get().equals(YdlDownloadState.EXECUTING)) {
			LOGGER.warn("tried to cancel, but no download was executing ");
			return;
		}
		executionFuture.getAndUpdate((f) -> {
			if (f != null) f.cancel(true);
			return null;
		});
		downloadState.compareAndSet(YdlDownloadState.EXECUTING, YdlDownloadState.CANCELED);
		onCancel();
		LOGGER.info("canceled download from url " + getUrl() + " to path " + outputFolder.getAbsolutePath());
	}

	public synchronized void execute() {
		prepareAndDownload();
		int result = -1;
		try {
			result = executionFuture.get().get();
		} catch (Exception e) {
			downloadState.compareAndSet(YdlDownloadState.EXECUTING, YdlDownloadState.ERROR);
			throw new RuntimeException(e);
		}
		onStateChanged();
		LOGGER.info("finished download from url " + getUrl() + " to path " + outputFolder.getAbsolutePath() + " with success code " + result);
	}

	private void prepareAndDownload() {
		LOGGER.info("starting download from url " + getUrl() + " to path " + outputFolder.getAbsolutePath());

		if (!isPrepared())
			prepare();

		if (downloadState.get().equals(YdlDownloadState.EXECUTING)) {
			throw new YdlDownloadError.IllegalDownloadState("can not start download with download-state:" + getDownloadState());
		}
		downloadState.set(YdlDownloadState.EXECUTING);
		onStateChanged();

		try {
			YoutubeDlExecutor.Builder builder = YoutubeDlExecutor.newBuilder(templateExecutor)
					.withStdoutConsumer(new StreamScanner(this::onStdout))
					.withStderrConsumer(new StreamScanner(this::onStderr))
					.onCompleted(status -> {
						YdlDownloadState exitDownloadState = status == 0 ?
								YdlDownloadState.SUCCESS
								: YdlDownloadState.ERROR;
						downloadState.compareAndSet(YdlDownloadState.EXECUTING, exitDownloadState);
						onStateChanged();
					});
			if (this.writeInfoJson)
				builder.withOptions(WRITE_INFO_JSON);

			YoutubeDlExecutor currentExecutor = builder.build();
			if (!executionFuture.compareAndSet(null, Executors.newSingleThreadExecutor().submit(currentExecutor)))
				throw new YdlDownloadError.IllegalDownloadState("can not submit another exection");
		} catch (Exception e) {
			LOGGER.error("error downloading ", e);
			downloadState.compareAndSet(YdlDownloadState.EXECUTING, YdlDownloadState.ERROR);
		}
		LOGGER.info("started download from url " + getUrl() + " to path " + outputFolder.getAbsolutePath());
	}

	public synchronized void prepare() {
		prepared.getAndSet(false);
		try {
			int result = YoutubeDlExecutor.newBuilder(templateExecutor)
					.withOptions(DUMP_SINGLE_JSON)
					.withStdoutConsumer(inputStream -> {
						try {
							String text = IOUtils.toString(inputStream, StandardCharsets.UTF_8.name());
							infoJsonAsText.getAndSet(text);
							YdlInfoJson ydlInfoJson = infoJsonParser.parse(text);
							ydlDownloadTaskMetadata.getAndSet(ydlInfoJson);
						} catch (IOException e) {
							LOGGER.error("prepare failed",e);
							throw new RuntimeException("error getting metadata", e);
						}
						
					})
					.build()
					.call();
			prepared.getAndSet(result == 0);

			this.singleInfoJsonMetadataAccessor = new SingleInfoJsonMetadataAccessor(ydlDownloadTaskMetadata.get());
			onPrepared();
		} catch (Exception e) {
			throw new RuntimeException("error getting metadata", e);
		}

	}

	private void onPrepared() {
		onPrepared.handleCallback(this, this.singleInfoJsonMetadataAccessor);
	}

	public boolean isPrepared() {
		return prepared.get();
	}

	private void onStderr(String s) {
		LOGGER.warn("youtube-dl wrote to stderr: " + s);
		if (this.onStderr != null)
			this.onStderr.handleCallback(this, YdlStatusMessage.createYdlStatusMessage(s));
	}


	private void onStdout(String nextLine) {
		LOGGER.debug("youtube-dl wrote to stdout: " + nextLine);

		YdlStatusMessage message = YdlStatusMessage.createYdlStatusMessage(nextLine);

		if (message instanceof NewDownloadStatusMessage) {
			NewDownloadStatusMessage downloadStatusMessage = (NewDownloadStatusMessage) message;
			onNewDownloadFile(downloadStatusMessage);
		}

		if (message instanceof ProgressStatusMessage) {
			ProgressStatusMessage progressStatusMessage = (ProgressStatusMessage) message;
			onProgress(progressStatusMessage);
		}


		if (this.onStdout != null)
			this.onStdout.handleCallback(this, message);

	}

	private void onProgress(ProgressStatusMessage progressStatusMessage) {
		YdlFileDownload fileDownload = this.currentDownload.get();
		if (fileDownload != null) {
			currentFilesizeMetadataAccessor.getFilesize(fileDownload).ifPresent((size) -> {
				fileDownload.updateDownloadedSize(size);
				this.onOutputFileChange.handleCallback(this, fileDownload);
			});
			if (fileDownload.getExpectedSize() == null || fileDownload.getExpectedSize() == 0) {
				fileDownload.updateExpectedSize(progressStatusMessage.getExpectedSize());
				this.onOutputFileChange.handleCallback(this, fileDownload);
			}
		}
	}

	private String getAbsoluteFilename(String filename) {
		return this.outputFolder.getAbsolutePath() + File.separator + filename;
	}

	private void onNewDownloadFile(NewDownloadStatusMessage message) {

		if (this.getCurrentDownload() != null)
			onDownloadFileFinished();

		String extension = message.getExtension();
		String filename = message.getFilename();
		String formatId = message.getFormatId();

		Long filesize = singleInfoJsonMetadataAccessor.getFilesize(filename, formatId).orElse(0L);
		YdlFileDownload download = YdlFileDownload.builder()
				.setExtension(extension)
				.setFilename(filename)
				.setExpectedSize(filesize)
				.setFormat(formatId)
				.setAbsoluteOutFilename(getAbsoluteFilename(filename))
				.createYdlFileDownload();

		this.currentDownload.getAndSet(download);
		this.getCurrentDownload().updateStarted(LocalTime.now());
		this.getCurrentDownload().updateState(YdlFileDownload.State.RUNNING);
		onStateChanged();
		if (this.onNewOutputFile != null)
			this.onNewOutputFile.handleCallback(this, this.currentDownload.get());
	}

	private void onDownloadFileFinished() {
		this.getCurrentDownload().updateFinished(LocalTime.now());
		this.getCurrentDownload().updateState(YdlFileDownload.State.FINISHED);
		onOutputFileChange.handleCallback(this, this.getCurrentDownload());
	}


	private void onStateChanged() {
		if (onStateChanged != null)
			onStateChanged.handleCallback(this, downloadState.get());
	}

	private void onCancel() {
		if (this.getCurrentDownload() != null) {
			this.getCurrentDownload().updateState(YdlFileDownload.State.CANCELED);
		}
		onCancel.handleCallback(this, this.getCurrentDownload());
	}

	public static YdlDownloadTaskBuilder builder() {
		return new YdlDownloadTaskBuilder();
	}

	public YdlDownloadState getDownloadState() {
		return downloadState.get();
	}

	public static class YdlDownloadTaskBuilder {

		private String pathToYdl = "/usr/local/bin/youtube-dl";
		private String url;
		private String outputFolder = ".";
		private boolean forceMp4 = false;

		private YdlCallback<YdlStatusMessage> onStdout = (a, b) -> {
		};
		private YdlCallback<YdlStatusMessage> onStderr = (a, b) -> {
		};
		private YdlCallback<YdlFileDownload> onNewOutputFile = (a, b) -> {
		};
		private YdlCallback<YdlDownloadState> onStateChanged = (a, b) -> {
		};
		private YdlCallback<YdlFileDownload> onOutputFileChange = (a, b) -> {
		};
		private YdlCallback<SingleInfoJsonMetadataAccessor> onPrepared = (a, b) -> {
		};
		private YdlCallback<YdlFileDownload> onCancel = (a, b) -> {
		};
		private boolean writeInfoJson;

		public YdlDownloadTask build() {
			return new YdlDownloadTask(pathToYdl, url, outputFolder, forceMp4, onStateChanged, onStdout, onStderr, onNewOutputFile, onOutputFileChange, onPrepared, writeInfoJson, onCancel);
		}

		public YdlDownloadTaskBuilder onOutputFileChange(YdlCallback<YdlFileDownload> onOutputFileChange) {
			this.onOutputFileChange = onOutputFileChange;
			return this;
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

		public YdlDownloadTaskBuilder onPrepared(YdlCallback<SingleInfoJsonMetadataAccessor> onPrepared) {
			this.onPrepared = onPrepared;
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

		public YdlDownloadTaskBuilder onStateChanged(YdlCallback<YdlDownloadState> onStateChanged) {
			this.onStateChanged = onStateChanged;
			return this;
		}

		public YdlDownloadTaskBuilder setWriteInfoJson(boolean writeInfoJson) {
			this.writeInfoJson = writeInfoJson;
			return this;
		}

		public YdlDownloadTaskBuilder onCancel(YdlCallback<YdlFileDownload> callback) {
			this.onCancel = callback;
			return this;
		}

		public YdlDownloadTaskBuilder setForceMp4(boolean forceMp4) {
			this.forceMp4 = forceMp4;
			return this;
		}
	}


	@FunctionalInterface
	public interface YdlCallback<T> {
		void handleCallback(YdlDownloadTask task, T object);
	}
}
