package io.github.lumue.ydlwrapper.download;

import java.time.LocalTime;

/**
 * provide information about downloaded files
 *
 * Created by lm on 10.03.16.
 */
public class YdlFileDownload {

	private final String filename;
	private final String format;
	private final String extension;
	private final Long expectedSize;
	private final String absoluteTempoutFilename;
	private Long downloadedSize;
	private Long bps;
	private LocalTime started;
	private LocalTime finished;
	private LocalTime lastUpdate;
	private State state=State.PENDING;
	private final String absoluteOutFilename;

	public YdlFileDownload updateDownloadedSize(Long downloadedSize) {
		refreshLastUpdate();
		this.downloadedSize = downloadedSize;
		return this;
	}

	private void refreshLastUpdate() {
		this.lastUpdate=LocalTime.now();
	}

	public YdlFileDownload updateBps(Long bps) {
		refreshLastUpdate();
		this.bps = bps;
		return this;
	}

	public YdlFileDownload updateStarted(LocalTime started) {
		refreshLastUpdate();
		this.started = started;
		return this;
	}

	public YdlFileDownload updateFinished(LocalTime finished) {
		refreshLastUpdate();
		this.finished = finished;
		return this;
	}

	public YdlFileDownload updateLastUpdate(LocalTime lastUpdate) {
		refreshLastUpdate();
		this.lastUpdate = lastUpdate;
		return this;
	}

	public YdlFileDownload updateState(State state) {
		refreshLastUpdate();
		this.state = state;
		return this;
	}

	public String getFilename() {
		return filename;
	}

	public String getFormat() {
		return format;
	}

	public String getExtension() {
		return extension;
	}

	public Long getExpectedSize() {
		return expectedSize;
	}

	public Long getDownloadedSize() {
		return downloadedSize;
	}

	public Long getBps() {
		return bps;
	}

	public LocalTime getStarted() {
		return started;
	}

	public LocalTime getFinished() {
		return finished;
	}

	public LocalTime getLastUpdate() {
		return lastUpdate;
	}

	public State getState() {
		return state;
	}

	public String getAbsoluteOutFilename() {
		return absoluteOutFilename;
	}

	public String getAbsoluteTempoutFilename() {
		return absoluteTempoutFilename;
	}

	public enum State{PENDING,RUNNING,PAUSED,FINISHED}

	@Override
	public String toString() {
		return "YdlFileDownload{" +
				"filename='" + filename + '\'' +
				", format='" + format + '\'' +
				", expectedSize=" + expectedSize +
				'}';
	}

	private YdlFileDownload(String filename, String extension, String format, Long expectedSize, String absoluteOutFilename, String absoluteTempoutFilename) {
		this.filename = filename;
		this.extension = extension;
		this.format = format;
		this.expectedSize = expectedSize;
		this.absoluteOutFilename = absoluteOutFilename;
		this.absoluteTempoutFilename = absoluteTempoutFilename;
	}

	public static YdlFileDownloadBuilder builder(){
		return new YdlFileDownloadBuilder();
	}

	public static class YdlFileDownloadBuilder {
		private String filename;
		private String format;
		private Long expectedSize;
		private String extension;
		private String absoluteOutFilename;
		private String absoluteTempoutFilename;

		public YdlFileDownloadBuilder setFilename(String filename) {
			this.filename = filename;
			return this;
		}

		public YdlFileDownloadBuilder setAbsoluteOutFilename(String filename) {
			this.absoluteOutFilename = filename;
			this.absoluteTempoutFilename=filename+".part";
			return this;
		}

		public YdlFileDownloadBuilder setFormat(String format) {
			this.format = format;
			return this;
		}

		public YdlFileDownloadBuilder setExpectedSize(Long expectedSize) {
			this.expectedSize = expectedSize;
			return this;
		}

		public YdlFileDownloadBuilder setExtension(String extension) {
			this.extension = extension;
			return this;
		}

		public YdlFileDownload createYdlFileDownload() {
			return new YdlFileDownload(filename, extension, format, expectedSize, absoluteOutFilename,absoluteTempoutFilename);
		}
	}


}
