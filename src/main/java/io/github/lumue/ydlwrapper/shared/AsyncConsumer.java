package io.github.lumue.ydlwrapper.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 * Async consuming
 * <p>
 * <p>
 * Created by lm on 11.03.16.
 */
public class AsyncConsumer<T> implements Consumer<T>, Closeable {

    private final static Logger LOGGER = LoggerFactory.getLogger(AsyncConsumer.class);

    private final Consumer<T> consumer;
    private ExecutorService executorService = Executors.newCachedThreadPool();

    public AsyncConsumer(Consumer<T> consumer) {
        Objects.requireNonNull(this.consumer = consumer);
    }

    @Override
    public void accept(T t) {
        Future<?> result = acceptAsync(t);
        try {
            result.get();
        } catch (InterruptedException e) {
            LOGGER.warn("execution interrupted", e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws IOException {

    }

    public Future<?> acceptAsync(T t) {
        return executorService.submit(() -> consumer.accept(t));
    }
}
