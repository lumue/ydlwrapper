package io.github.lumue.ydlwrapper.metadata;

/**
 * Created by lm on 12.03.16.
 */
public class NewDownloadStatusMessage extends YdlStatusMessage{

	private static final String NEW_FILE_STATUS_PREFIX = DOWNLOAD_STATUS_PREFIX+" Destination: ";
	private static final int NEW_FILE_STATUS_PREFIX_LENGTH = NEW_FILE_STATUS_PREFIX.length();

	public NewDownloadStatusMessage(String line) {
		super(line);
	}
	public String parseFilename() {
		return getLine().substring(NEW_FILE_STATUS_PREFIX_LENGTH);
	}

	public String parseExtension() {
		return getLine().substring(getLine().lastIndexOf('.') +1).replace("\"","");
	}

	public String parseFormatId() {
		return getLine().substring(getLine().lastIndexOf(".f") +2,getLine().lastIndexOf('.'));
	}

	protected static boolean isMessageTypeFor(String line) {
		return line.startsWith(NEW_FILE_STATUS_PREFIX);
	}

}
