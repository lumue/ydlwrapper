
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

/**
 * Created by lm on 06.03.16.
 */
public class YdlOutput implements Iterable<String>{

	private final Deque<String> lines=new ConcurrentLinkedDeque<>();

	private final AtomicReference<YdlDownloadProgress> lastProgressReference=new AtomicReference<>();
	private final YdlDownloadProgressParser progressParser=new YdlDownloadProgressParser();

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

			return getProgressParser().parse(lastMessage());
		});
	}

	public YdlDownloadProgressParser getProgressParser() {
		return progressParser;
	}
}
