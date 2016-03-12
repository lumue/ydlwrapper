package io.github.lumue.ydlwrapper.download;

import java.io.Serializable;
import java.util.Objects;

/**
 * Created by lm on 10.03.16.
 */
public class YdlStatusMessage implements Serializable{

	private static final String NEW_FILE_STATUS_PREFIX = "[download] Destination: ";
	private static final int NEW_FILE_STATUS_PREFIX_LENGTH = NEW_FILE_STATUS_PREFIX.length();

	private final String line;

	public YdlStatusMessage(String line) {
		this.line = Objects.requireNonNull(line,"input line must not be null");
	}

	public boolean isNewOutputFileSignal(){
		return line.startsWith(NEW_FILE_STATUS_PREFIX);
	}

	@Override
	public String toString() {
		return line;
	}


	public String parseFilename() {
		return line.substring(NEW_FILE_STATUS_PREFIX_LENGTH);
	}

	public String parseExtension() {

		return line.substring(line.lastIndexOf('.') +1);
	}
}
