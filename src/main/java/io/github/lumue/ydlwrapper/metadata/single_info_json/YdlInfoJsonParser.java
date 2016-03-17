package io.github.lumue.ydlwrapper.metadata.single_info_json;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
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

			BufferedReader br=null;
			try {
				br = new BufferedReader(new InputStreamReader(stream));
				return objectMapper.readValue(stream,YdlInfoJson.class);
			} catch (Error|RuntimeException|IOException e) {
				try {
					if(br!=null)
						br.close();
				} catch (IOException ex) {
					try {
						e.addSuppressed(ex);
					} catch (Throwable ignore) {}
				}
				throw new RuntimeException(e);
			}
	}

	/**
	 * Convert a Closeable to a Runnable by converting checked IOException
	 * to UncheckedIOException
	 */
	private static Runnable asUncheckedRunnable(Closeable c) {
		return () -> {
			try {
				c.close();
			} catch (IOException e) {
				throw new UncheckedIOException(e);
			}
		};
	}

}
