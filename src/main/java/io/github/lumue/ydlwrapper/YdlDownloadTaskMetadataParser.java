package io.github.lumue.ydlwrapper;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lm on 10.03.16.
 */
public class YdlDownloadTaskMetadataParser {

	private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();
	private final ObjectMapper objectMapper;

	public YdlDownloadTaskMetadataParser(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public YdlDownloadTaskMetadataParser() {
		this(DEFAULT_MAPPER);
	}

	public YdlDownloadTaskMetadata parse(InputStream stream) throws IOException {
		return objectMapper.readValue(stream,YdlDownloadTaskMetadata.class);
	}
}
