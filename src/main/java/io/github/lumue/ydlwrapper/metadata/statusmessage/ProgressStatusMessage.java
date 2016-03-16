package io.github.lumue.ydlwrapper.metadata.statusmessage;

/**
 * Created by lm on 12.03.16.
 */
public class ProgressStatusMessage extends YdlStatusMessage {
	public ProgressStatusMessage(String line) {
		super(line);
	}


	protected static boolean isMessageTypeFor(String line) {
		return line.contains("ETA");
	}



}
