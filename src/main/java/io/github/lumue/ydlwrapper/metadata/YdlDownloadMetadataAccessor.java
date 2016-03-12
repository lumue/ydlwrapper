package io.github.lumue.ydlwrapper.metadata;

/**
 * Created by lm on 12.03.16.
 */
public interface YdlDownloadMetadataAccessor {
	Long getFilesize(String filename, String formatExtension);

	boolean isPlaylist();
}
