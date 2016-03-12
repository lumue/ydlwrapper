package io.github.lumue.ydlwrapper.metadata.single_info_json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.function.Function;

/**
 * Created by lm on 10.03.16.
 */
public class YdlInfoJsonParser implements Function<InputStream,YdlInfoJson>{

	private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();
	private final ObjectMapper objectMapper;

	public YdlInfoJsonParser(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public YdlInfoJsonParser() {
		this(DEFAULT_MAPPER);
	}

	@Override
	public YdlInfoJson apply(InputStream stream)  {
		try {
			return objectMapper.readValue(stream,YdlInfoJson.class);
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

}
