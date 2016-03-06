/**
 * Created by lm on 06.03.16.
 */
public class YdlDownloadError extends Exception {
	/**
	 * Created by lm on 06.03.16.
	 */
	public static class IllegalDownloadState extends RuntimeException {
		public IllegalDownloadState(String message) {
			super(message);
		}
	}


	public YdlDownloadError(String message) {
		super(message);
	}
}

