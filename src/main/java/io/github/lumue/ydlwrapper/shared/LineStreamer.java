package io.github.lumue.ydlwrapper.shared;

import java.io.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by lm on 11.03.16.
 */
public class LineStreamer {

	private final InputStream stream;

	public LineStreamer(InputStream stream) {
		this.stream = stream;
	}

	public Stream<String> lines(){
		BufferedReader br=null;
		try {
			br = new BufferedReader(new InputStreamReader(stream));
			return br.lines().onClose(asUncheckedRunnable(br));
		} catch (Error|RuntimeException e) {
			try {
				if(br!=null)
					br.close();
			} catch (IOException ex) {
				try {
					e.addSuppressed(ex);
				} catch (Throwable ignore) {}
			}
			throw e;
		}
	}

	/**
	 * Convert a Closeable to a Runnable by converting checked IOException
	 * to UncheckedIOException
	 */
	private static Runnable asUncheckedRunnable(Closeable c) {
		return () -> {
			try {
				c.close();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		};
	}
}
