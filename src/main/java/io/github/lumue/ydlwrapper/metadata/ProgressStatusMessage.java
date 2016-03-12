package io.github.lumue.ydlwrapper.metadata;

/**
 * Created by lm on 12.03.16.
 */
public class ProgressStatusMessage extends YdlStatusMessage {
	public ProgressStatusMessage(String line) {
		super(line);
	}


	protected static boolean isMessageTypeFor(String line) {
		return false;
	}

}
