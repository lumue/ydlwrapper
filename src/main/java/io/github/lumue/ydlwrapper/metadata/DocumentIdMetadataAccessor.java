package io.github.lumue.ydlwrapper.metadata;

import java.util.Optional;

/**
 * Get the download document id
 * Created by lm on 12.03.16.
 */
@FunctionalInterface
public interface DocumentIdMetadataAccessor {
	/**
	 * get the download Title
	 * @return
	 */
	Optional<String> getDocumentId();


}