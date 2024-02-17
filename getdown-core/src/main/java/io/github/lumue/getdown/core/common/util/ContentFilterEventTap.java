package io.github.lumue.getdown.core.common.util;

import reactor.bus.Event;
import reactor.bus.EventBus;
import reactor.fn.Consumer;

import java.util.Objects;
import java.util.function.Predicate;

/**
 *
 * Listens to Events matching a given selector and matches them to a given
 * predicate
 * events are either ignored or repackaged and forwarded with a new selector key
 *
 * @author lm
 *
 * @param <T>
 */
public class ContentFilterEventTap<T> implements Consumer<Event<T>> {

private final EventBus eventbus;

private final String forwardSelectorKey;

private final Predicate<T> predicate;

public ContentFilterEventTap(EventBus eventbus, String forwardSelectorKey,  Predicate<T> predicate) {
	super();
	this.eventbus = eventbus;
	this.forwardSelectorKey = forwardSelectorKey;
	this.predicate = predicate;
}



@Override
public void accept(Event<T> t) {
	
	if (!predicate.test(t.getData()))
		return;
	
	eventbus.notify(forwardSelectorKey, Event.wrap(Objects.requireNonNull(t.getData())));
}


}
