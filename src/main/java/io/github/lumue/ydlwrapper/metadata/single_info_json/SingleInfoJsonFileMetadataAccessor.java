package io.github.lumue.ydlwrapper.metadata.single_info_json;

import io.github.lumue.ydlwrapper.metadata.FileMetadataAccessor;

import java.util.Objects;

/**
 * Created by lm on 12.03.16.
 */
public class SingleInfoJsonFileMetadataAccessor implements FileMetadataAccessor {
	private final YdlInfoJson ydlInfoJson;

	public SingleInfoJsonFileMetadataAccessor(YdlInfoJson ydlInfoJson) {
		this.ydlInfoJson = Objects.requireNonNull(ydlInfoJson);
	}

	@Override
	public Long getFilesize(String filename, String formatExtension) {
		return 0L;
	}
}
