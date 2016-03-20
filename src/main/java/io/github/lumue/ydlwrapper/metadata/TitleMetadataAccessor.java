package io.github.lumue.ydlwrapper.metadata;


import java.util.Optional;

/**
 * Get the download title
 * Created by lm on 12.03.16.
 */
@FunctionalInterface
public interface TitleMetadataAccessor {
	/**
	 * get the download Title
	 * @return
	 */
	Optional<String> getTitle();


}