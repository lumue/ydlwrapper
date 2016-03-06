import java.time.LocalTime;
import java.util.Objects;

/**
 * Created by lm on 06.03.16.
 */
public class YdlDownloadProgressParser {

	private final String message;


	public YdlDownloadProgressParser(String message) {
		this.message = Objects.requireNonNull(message,"message must not be null");
	}

	public YdlDownloadProgress parse() {
		return YdlDownloadProgress.builder()
				.setBps(parseBps())
				.setEta(parseETA())
				.setPercentage(parsePercentage())
				.setFileSize(parseFilesize())
				.setRaw(message)
				.build();
	}

	private long parseFilesize() {
		return 0;
	}

	private long parsePercentage() {
		return 0;
	}

	private LocalTime parseETA() {
		return null;
	}

	private long parseBps() {
		return 0;
	}
}
