package io.github.lumue.ydlwrapper.shared;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Consumer;

/**
 * Created by lm on 11.03.16.
 */
public class YoutubeDlExecutor {

	private final String ydlLocation;

	private final String url;

	private final Consumer<InputStream> stdoutConsumer;

	private final Consumer<InputStream> stderrConsumer;

	private final Set<Option> options;

	private final File outputFolder;

	

	private YoutubeDlExecutor(Builder builder) {
		ydlLocation = builder.ydlLocation;
		url = builder.url;
		stdoutConsumer = builder.stdoutConsumer;
		stderrConsumer = builder.stderrConsumer;
		outputFolder = builder.outputFolder;
		options=builder.options;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	public static Builder newBuilder( YoutubeDlExecutor other) {
		Builder builder = new Builder();

		builder.ydlLocation = other.ydlLocation;
		builder.url = other.url;
		builder.stdoutConsumer = other.stdoutConsumer;
		builder.stderrConsumer = other.stderrConsumer;
		builder.options.addAll(builder.options);
		builder.outputFolder = other.outputFolder;

		return builder;
	}


	public enum Option{
		DUMP_SINGLE_JSON{
			@Override
			public String toString(){
				return "--dump-single-json";
			}
		},
		WRITE_INFO_JSON{
			@Override
			public String toString(){
				return "--write-info-json";
			}
		},
		NO_COLOR{
			@Override
			public String toString(){
				return "--no-color";
			}
		},
		NEW_LINE{
			@Override
			public String toString(){
				return "--newline";
			}
		};

		private static String toString(Collection<Option> options){
			StringBuilder sb=new StringBuilder(" ");
			options.forEach(option -> sb.append(option).append(" "));
			sb.append(" ");
			return sb.toString();
		};
	};


	/**
	 * execute the youtube-dl binary with the configured options.
	 * stderr and stdout streams will be consumed in extra threads.  
	 * @return exit from youtube-dl
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public int execute() throws IOException, InterruptedException {
		Process p;
		String command=this.ydlLocation+Option.toString(options)+this.url;
		p = Runtime.getRuntime().exec(command,null,outputFolder);

		if(stderrConsumer!=null) {
			AsyncConsumer<InputStream> stderrScanner = new AsyncConsumer<>(this.stderrConsumer);
			stderrScanner.accept(p.getErrorStream());
		}
		if(stdoutConsumer!=null){
			Consumer<InputStream> stdoutScanner = this.stdoutConsumer;
			stdoutScanner.accept(p.getInputStream());
		}

		return p.waitFor();
	}

	public static final class Builder {
		private String ydlLocation;
		private String url;
		private Consumer<InputStream> stdoutConsumer;
		private Consumer<InputStream> stderrConsumer;
		private final Set<Option> options=new HashSet<>();
		private File outputFolder;

		private Builder() {
		}




		public Builder withYdlLocation( String val) {
			ydlLocation = val;
			return this;
		}

		
		public Builder withUrl( String val) {
			url = val;
			return this;
		}

		
		public Builder withStdoutConsumer( Consumer<InputStream> val) {
			stdoutConsumer = val;
			return this;
		}

		
		public Builder withStderrConsumer( Consumer<InputStream> val) {
			stderrConsumer = val;
			return this;
		}

		
		public Builder withOptions( Option... val) {
			options.addAll(Arrays.asList(val));
			return this;
		}

		
		public Builder withOutputFolder( File val) {
			outputFolder = val;
			return this;
		}

		
		public YoutubeDlExecutor build() {
			return new YoutubeDlExecutor(this);
		}
	}
}
