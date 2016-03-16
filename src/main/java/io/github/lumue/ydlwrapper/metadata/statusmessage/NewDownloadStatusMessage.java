package io.github.lumue.ydlwrapper.metadata.statusmessage;

import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.lumue.ydlwrapper.shared.Lazy.lazily;
import static io.github.lumue.ydlwrapper.shared.Lazy.value;

/**
 * Created by lm on 12.03.16.
 */
public class NewDownloadStatusMessage extends YdlStatusMessage{

	private static final String NEW_FILE_STATUS_PREFIX = DOWNLOAD_STATUS_PREFIX+" Destination: ";
	private static final int NEW_FILE_STATUS_PREFIX_LENGTH = NEW_FILE_STATUS_PREFIX.length();

	private final static Function<String,String> FILENAME_PARSER= line -> line.substring(NEW_FILE_STATUS_PREFIX_LENGTH);
	private final static Function<String,String> EXTENSION_PARSER= line -> line.substring(line.lastIndexOf('.') +1).replaceAll("\"","");
	private static final Function<String,String> FORMAT_ID_PARSER = line -> line.substring(line.lastIndexOf(".f") +2,line.lastIndexOf('.'));;

	private Supplier<String> filenameSupplier= lazily(() -> filenameSupplier=value(FILENAME_PARSER.apply(getLine())));
	private Supplier<String> extensionSupplier= lazily(() -> filenameSupplier=value(EXTENSION_PARSER.apply(getLine())));
	private Supplier<String> formatIdSupplier= lazily(() -> filenameSupplier=value(FORMAT_ID_PARSER.apply(getLine())));

	public NewDownloadStatusMessage(String line) {
		super(line);
	}
	public String getFilename() {
		return filenameSupplier.get();
	}

	public String getExtension() {
		return extensionSupplier.get();
	}

	public String getFormatId() {
		return formatIdSupplier.get();
	}

	protected static boolean isMessageTypeFor(String line) {
		return line.startsWith(NEW_FILE_STATUS_PREFIX);
	}


}
