package io.github.lumue.ydlwrapper.metadata;

/**
 * Created by lm on 12.03.16.
 */
public interface FileMetadataAccessor {
	Long getFilesize(String filename, String formatExtension);
}
