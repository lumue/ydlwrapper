package io.github.lumue.ydlwrapper.metadata.statusmessage;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by lm on 10.03.16.
 */
public class YdlStatusMessage implements Serializable {

    protected static final String DOWNLOAD_STATUS_PREFIX = "[download]";


    public String getLine() {
        return line;
    }

    private final String line;

    private final LocalDateTime timestamp;

    protected YdlStatusMessage(String line) {
        this.line = line;
        timestamp = LocalDateTime.now();
    }

    public static YdlStatusMessage createYdlStatusMessage(String line) {
        Objects.requireNonNull(line, "input line must not be null");

        if (NewDownloadStatusMessage.isMessageTypeFor(line))
            return new NewDownloadStatusMessage(line);
        else if (ProgressStatusMessage.isMessageTypeFor(line))
            return new ProgressStatusMessage(line);
        return new YdlStatusMessage(line);
    }


    @Override
    public String toString() {
        return line;
    }


}
