package io.github.lumue.ydlwrapper;

import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * capture output from youtube-dl process
 *
 * Created by lm on 06.03.16.
 */
public class YdlOutput implements Iterable<String>{

	private final Deque<String> lines=new ConcurrentLinkedDeque<>();

	private final AtomicReference<YdlDownloadProgress> lastProgressReference=new AtomicReference<>();

	public void append(String line) {
		lastProgressReference.set(null);
		lines.addLast(line);
	}


	public void clear() {
		lines.clear();
	}

	public String lastMessage(){
		return lines.getLast();
	};

	@Override
	public Iterator<String> iterator() {
		return lines.iterator();
	}

	public Stream<String> stream(){
		return lines.stream();
	}

	public YdlDownloadProgress lastProgress() {
		return lastProgressReference.updateAndGet(currentProgress -> {

			if (currentProgress != null)
				return currentProgress;
			
			if(lines.isEmpty())
				return null;

			return new YdlDownloadProgressParser(lastMessage()).parse();
		});
	}


}
