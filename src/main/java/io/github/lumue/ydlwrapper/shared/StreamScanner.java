package io.github.lumue.ydlwrapper.shared;

import java.io.InputStream;
import java.util.function.Consumer;

/**
 * consume a stream line by line
 * Created by lm on 10.03.16.
 */
public class StreamScanner implements Consumer<InputStream> {


    private final Consumer<String> lineConsumer;

    public StreamScanner(Consumer<String> lineConsumer) {
        this.lineConsumer = lineConsumer;
    }

    @Override
    /**
     * consumes the stream line by line and delegates line processing to lineConsumer
     */
    public void accept(InputStream stream) {

        new LineStreamer(stream).lines().forEach(lineConsumer);
    }

}
