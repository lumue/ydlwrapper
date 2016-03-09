package io.github.lumue.ydlwrapper;

/**
 * Singleton
 *
 * Created by lm on 06.03.16.
 */
public enum YdlBinaryDiscoverer {
	INSTANCE;

	public String discoverYdlBinary() {
		return "/usr/local/bin/youtube-dl";
	}
}
