package io.github.lumue.ydlwrapper.metadata.single_info_json;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by lm on 10.03.16.
 */
public class YdlInfoJsonParser {

    private static final ObjectMapper DEFAULT_MAPPER = new ObjectMapper();
    private final ObjectMapper objectMapper;

    private YdlInfoJsonParser(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public YdlInfoJsonParser() {
        this(DEFAULT_MAPPER);
    }


    public YdlInfoJson parse(InputStream stream) {
        try {
            return objectMapper.readValue(stream, YdlInfoJson.class);
        } catch (Error | RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public YdlInfoJson parse(String string) {
        try {
            return objectMapper.readValue(string, YdlInfoJson.class);
        } catch (Error | RuntimeException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
