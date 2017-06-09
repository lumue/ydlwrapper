package io.github.lumue.ydlwrapper.shared;

import java.io.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Created by lm on 11.03.16.
 */
class LineStreamer {

	private final InputStream stream;

	LineStreamer(InputStream stream) {
		this.stream = stream;
	}

	Stream<String> lines(){

		try (final BufferedReader br = new BufferedReader(new InputStreamReader(stream))){

			return br.lines().onClose(asUncheckedRunnable(br));
		} catch (IOException ex) {
			throw new RuntimeException(ex);
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
