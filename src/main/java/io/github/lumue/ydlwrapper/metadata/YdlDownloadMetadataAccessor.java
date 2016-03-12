package io.github.lumue.ydlwrapper.metadata;

import java.util.Optional;

/**
 * Created by lm on 12.03.16.
 */
public interface YdlDownloadMetadataAccessor {
	Optional<Long> getFilesize(String filename, String formatExtension);

	boolean isPlaylist();
}
