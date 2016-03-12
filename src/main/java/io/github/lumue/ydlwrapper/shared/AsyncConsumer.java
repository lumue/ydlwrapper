package io.github.lumue.ydlwrapper.shared;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Consumer;

/**
 * Async consuming
 *
 *
 * Created by lm on 11.03.16.
 */
public class AsyncConsumer<T> implements Consumer<T> {


	private final Consumer<T> consumer;

	public AsyncConsumer(Consumer<T> consumer) {
		Objects.requireNonNull(this.consumer = consumer);
	}

	@Override
	public void accept(T t) {
		final Executor executor = Executors.newSingleThreadExecutor();
		executor.execute(() ->consumer.accept(t));
	}
}
