package io.github.lumue.ydlwrapper;

import java.io.InputStream;
import java.util.Scanner;
import java.util.function.Consumer;

/**
 * Wait for new lines on an inputstream
 * Created by lm on 10.03.16.
 */
class StreamScanner {

	private final InputStream stream;

	private final Consumer<String> lineConsumer;

	StreamScanner(InputStream stream, Consumer<String> lineConsumer) {
		this.stream = stream;
		this.lineConsumer = lineConsumer;
	}

	void scan() {
		Scanner s = new Scanner(stream);
		while (s.hasNextLine()) {
			String nextLine = s.nextLine();
			lineConsumer.accept(nextLine);
		}
		s.close();
	}

}
