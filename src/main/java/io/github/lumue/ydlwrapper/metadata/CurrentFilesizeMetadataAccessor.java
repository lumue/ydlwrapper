package io.github.lumue.ydlwrapper.metadata;

import io.github.lumue.ydlwrapper.download.YdlFileDownload;

import java.io.File;
import java.util.Optional;

/**
 * Get the actual downloaded filesize for a {@link YdlFileDownload}
 * Created by lm on 12.03.16.
 */
@FunctionalInterface
public interface CurrentFilesizeMetadataAccessor {
	/**
	 * get the filesize if possible, or null
	 * @param fileDownload
	 * @return
	 */
	Optional<Long> getFilesize(YdlFileDownload fileDownload);


}
