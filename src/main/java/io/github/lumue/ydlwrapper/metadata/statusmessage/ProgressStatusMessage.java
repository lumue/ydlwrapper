package io.github.lumue.ydlwrapper.metadata.statusmessage;

import io.github.lumue.ydlwrapper.shared.HumanReadableByteParser;

import java.math.BigDecimal;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.lumue.ydlwrapper.shared.Lazy.lazily;
import static io.github.lumue.ydlwrapper.shared.Lazy.value;

/**
 * extract progression stats from youtube-dl output
 *
 *
 * Created by lm on 12.03.16.
 */
public class ProgressStatusMessage extends YdlStatusMessage {

	private static final int PREFIX_LENGTH=DOWNLOAD_STATUS_PREFIX.length();

	private final static Function<String,Long> BYTE_PARSER=new HumanReadableByteParser();

	private final static Function<String,BigDecimal> PERCENTAGE_PARSER=( line ->{
		String percentageString=line
				.substring(PREFIX_LENGTH,line.indexOf("%"))
				.trim();
		return new BigDecimal(percentageString);
	} );

	private final static Function<String,Long> SIZE_PARSER=( line ->{
		String sizeString=line
				.substring(line.indexOf("of ")+3,line.indexOf(" at "))
				.trim();
		return BYTE_PARSER.apply(sizeString);
	} );

	private Supplier<BigDecimal> progressPercentageSupplier= lazily(() -> progressPercentageSupplier=value(PERCENTAGE_PARSER.apply(getLine())));
	private Supplier<Long> sizeSupplier= lazily(() -> sizeSupplier=value(SIZE_PARSER.apply(getLine())));

	public BigDecimal getPercentage() {
		return progressPercentageSupplier.get();
	}

	public ProgressStatusMessage(String line) {
		super(line);
	}


	public static boolean isMessageTypeFor(String line) {
		return line.startsWith(DOWNLOAD_STATUS_PREFIX)
				&& line.contains("ETA")
				&& line.contains("%");
	}

	public long getExpectedSize() {
		return sizeSupplier.get();
	}
}
