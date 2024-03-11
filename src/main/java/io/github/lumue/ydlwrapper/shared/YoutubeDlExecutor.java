package io.github.lumue.ydlwrapper.shared;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.Arrays.stream;

/**
 * Created by lm on 11.03.16.
 */
public class YoutubeDlExecutor implements Callable<Integer> {

    private final static Logger LOGGER = LoggerFactory.getLogger(YoutubeDlExecutor.class);

    private final String ydlLocation;

    private final String url;

    private final Consumer<InputStream> stdoutConsumer;

    private final Consumer<InputStream> stderrConsumer;

    private final Set<String> options;

    private final File outputFolder;

    private final CompletedCallback completedCallback;

    private final AtomicInteger result = new AtomicInteger();


    public interface CompletedCallback {
        void onCompleted(int status);
    }


    private YoutubeDlExecutor(Builder builder) {
        ydlLocation = builder.ydlLocation;
        url = builder.url;
        stdoutConsumer = builder.stdoutConsumer;
        stderrConsumer = builder.stderrConsumer;
        outputFolder = builder.outputFolder;
        options = builder.options;
        completedCallback = builder.completedCallback;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(YoutubeDlExecutor other) {
        Builder builder = new Builder();

        builder.ydlLocation = other.ydlLocation;
        builder.url = other.url;
        builder.stdoutConsumer = other.stdoutConsumer;
        builder.stderrConsumer = other.stderrConsumer;
        builder.options.addAll(other.options);
        builder.outputFolder = other.outputFolder;

        return builder;
    }


    public enum Option {



        
        FORCE_MP4 {
            @Override
            public String toString() {
                return "-f bestvideo[ext=mp4]+bestaudio[ext=m4a]/mp4";
            }
        },
        DUMP_SINGLE_JSON {
            @Override
            public String toString() {
                return "--dump-single-json";
            }
        },
        WRITE_INFO_JSON {
            @Override
            public String toString() {
                return "--write-info-json";
            }
        },
        NO_COLOR {
            @Override
            public String toString() {
                return "--no-color";
            }
        },
        NEW_LINE {
            @Override
            public String toString() {
                return "--newline";
            }
        }
    }


    /**
     * execute the youtube-dl binary with the configured options.
     * stderr and stdout streams will be consumed in extra threads.
     *
     * @return exit from youtube-dl
     */
    public Integer call() {

        String command = YoutubeDlExecutor.this.ydlLocation +
                " "+options.stream().collect(Collectors.joining(" ")) +
                " --output %(title)s.f%(format_id)s.%(ext)s "+
                YoutubeDlExecutor.this.url;

        return call(command);

    }

    private Integer call(String command) {
        Process process = null;
        result.set(-1);

        try {


            process = Runtime.getRuntime().exec(command, null, outputFolder);


            Future<?> stderrFuture = null;
            Future<?> stdoutFuture = null;
            if (stderrConsumer != null) {
                AsyncConsumer<InputStream> stderrScanner = new AsyncConsumer<>(YoutubeDlExecutor.this.stderrConsumer);
                stderrFuture = stderrScanner.acceptAsync(process.getErrorStream());
            }
            if (stdoutConsumer != null) {
                AsyncConsumer<InputStream> stdoutScanner = new AsyncConsumer<>(YoutubeDlExecutor.this.stdoutConsumer);
                stdoutFuture = stdoutScanner.acceptAsync(process.getInputStream());
            }

            stdoutFuture.get();
            if (stderrFuture != null)
                stderrFuture.get();
            result.set(process.waitFor());
        } catch (InterruptedException e) {
            //thread was interrupted.
            if (process != null) {
                process.destroy();
            }
            //reset interrupted flag
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        completedCallback.onCompleted(result.get());
        return result.get();
    }

    public static final class Builder {
        private String ydlLocation;
        private String url;
        private Consumer<InputStream> stdoutConsumer;
        private Consumer<InputStream> stderrConsumer;
        private final Set<String> options = new HashSet<>();
        private File outputFolder;
        private CompletedCallback completedCallback = status -> {
        };

        private Builder() {
        }


        public Builder withYdlLocation(String val) {
            ydlLocation = val;
            return this;
        }


        public Builder withUrl(String val) {
            url = val;
            return this;
        }


        public Builder withStdoutConsumer(Consumer<InputStream> val) {
            stdoutConsumer = val;
            return this;
        }


        public Builder withStderrConsumer(Consumer<InputStream> val) {
            stderrConsumer = val;
            return this;
        }


        public Builder withOptions(Option... val) {
            options.addAll(
                    stream(val)
                    .map(Enum::toString)
                    .collect(Collectors.toSet())
            );
            return this;
        }

        public Builder withOptions(String... val) {
            options.addAll(Arrays.asList(val));
            return this;
        }


        public Builder withOutputFolder(File val) {
            outputFolder = val;
            return this;
        }

        public Builder onCompleted(CompletedCallback callback) {
            completedCallback = callback;
            return this;
        }


        public YoutubeDlExecutor build() {
            return new YoutubeDlExecutor(this);
        }
    }


}
