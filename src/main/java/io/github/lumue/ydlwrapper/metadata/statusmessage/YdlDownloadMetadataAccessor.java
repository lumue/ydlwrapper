package io.github.lumue.ydlwrapper.metadata.statusmessage;

import java.util.Optional;

/**
 * Created by lm on 12.03.16.
 */
public interface YdlDownloadMetadataAccessor {
	Optional<Long> getFilesize(String filename, String formatExtension);

	boolean isPlaylist();
}
