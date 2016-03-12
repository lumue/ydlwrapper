package io.github.lumue.ydlwrapper.metadata;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.function.Function;

/**
 * Created by lm on 10.03.16.
 */
public class YdlDownloadTaskMetadataParser implements Function<InputStream,YdlDownloadTaskMetadata>{

	private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();
	private final ObjectMapper objectMapper;

	public YdlDownloadTaskMetadataParser(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public YdlDownloadTaskMetadataParser() {
		this(DEFAULT_MAPPER);
	}

	@Override
	public YdlDownloadTaskMetadata apply(InputStream stream)  {
		try {
			return objectMapper.readValue(stream,YdlDownloadTaskMetadata.class);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
