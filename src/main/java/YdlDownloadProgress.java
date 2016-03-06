import java.time.LocalTime;

/**
 * represents a status output from youtube-dl
 * the raw output looks like this "[download]   2.7% of 241.60MiB at  1.47MiB/s ETA 02:40"
 * Created by lm on 06.03.16.
 */
public class YdlDownloadProgress {

	private final String raw;

	private final long percentage;

	private final long fileSize;

	private final long bps;

	private final LocalTime eta;


	YdlDownloadProgress(String raw, long percentage, long fileSize, long bps, LocalTime eta) {
		this.raw = raw;
		this.percentage = percentage;
		this.fileSize = fileSize;
		this.bps = bps;
		this.eta = eta;
	}


	public static class YdlDownloadProgressBuilder {
		private String raw;
		private long percentage;
		private long fileSize;
		private long bps;
		private LocalTime eta;

		public YdlDownloadProgressBuilder setRaw(String raw) {
			this.raw = raw;
			return this;
		}

		public YdlDownloadProgressBuilder setPercentage(long percentage) {
			this.percentage = percentage;
			return this;
		}

		public YdlDownloadProgressBuilder setFileSize(long fileSize) {
			this.fileSize = fileSize;
			return this;
		}

		public YdlDownloadProgressBuilder setBps(long bps) {
			this.bps = bps;
			return this;
		}

		public YdlDownloadProgressBuilder setEta(LocalTime eta) {
			this.eta = eta;
			return this;
		}

		public YdlDownloadProgress createYdlDownloadProgress() {
			return new YdlDownloadProgress(raw, percentage, fileSize, bps, eta);
		}
	}
}
