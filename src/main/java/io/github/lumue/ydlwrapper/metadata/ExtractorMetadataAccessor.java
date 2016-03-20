package io.github.lumue.ydlwrapper.metadata;

import java.util.Optional;

/**
 * Get the extractor for this download
 * Created by lm on 12.03.16.
 */
@FunctionalInterface
public interface ExtractorMetadataAccessor {
	/**
	 * get the download Title
	 * @return
	 */
	Optional<String> getExtractor();


}