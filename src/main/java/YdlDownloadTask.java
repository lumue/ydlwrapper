import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

/**
 * Execute youtube-dl download
 *
 * Created by lm on 06.03.16.
 */
public class YdlDownloadTask {


	private final YdlBinaryDiscoverer ydlDiscoverer= YdlBinaryDiscoverer.INSTANCE;

	private final Logger LOGGER= LoggerFactory.getLogger(YdlDownloadTask.class);

	public enum YdlDownloadState{EXECUTING, ERROR, SUCCESS, PENDING}

	private final String url;

	private final File outputFolder;

	private final Consumer<YdlDownloadTask> onChangeCallback;

	private final AtomicReference<YdlDownloadState> downloadState=new AtomicReference<>(YdlDownloadState.PENDING);


	private final YdlOutput ydlOutput =new YdlOutput();


	public YdlDownloadTask(String url, String outputFolder, Consumer<YdlDownloadTask> onChangeCallback) {
		this.url = Objects.requireNonNull(url,"url must not be null");
		this.outputFolder = new File(Objects.requireNonNull(outputFolder));
		this.onChangeCallback = onChangeCallback;
	}

	public String getUrl() {
		return url;
	}

	public YdlDownloadProgress getProgress(){
		return ydlOutput.lastProgress();
	}

	public void execute()  {

		LOGGER.info("starting download from url "+getUrl()+" to path "+outputFolder.getAbsolutePath());

		if(!downloadState.compareAndSet(YdlDownloadState.PENDING,YdlDownloadState.EXECUTING)){
			throw new YdlDownloadError.IllegalDownloadState("can not start download with download-state:"+getDownloadState());
		}
		ydlOutput.clear();
		triggerOnChangeCallback();

		int result=-1;
		try {
			Process p;
			String command=discoverYdlBinary()+" "+getUrl();
			p = Runtime.getRuntime().exec(command,null,outputFolder);

			Scanner s = new Scanner(p.getInputStream());
			while (s.hasNextLine()) {
				String line = s.nextLine();
				if(LOGGER.isDebugEnabled())
					LOGGER.debug("youtube-dl process "+p.toString()+" wrote to stdout: "+ line);
				ydlOutput.append(line);
				triggerOnChangeCallback();
			}
			s.close();

			result=p.waitFor();

			YdlDownloadState exitDownloadState=result==0?
					YdlDownloadState.SUCCESS
					:YdlDownloadState.ERROR;
			downloadState.compareAndSet(YdlDownloadState.PENDING,exitDownloadState);
		} catch (Exception e) {
			downloadState.compareAndSet(YdlDownloadState.PENDING,YdlDownloadState.ERROR);
		}
		triggerOnChangeCallback();
		LOGGER.info("finished download from url "+getUrl()+" to path "+outputFolder.getAbsolutePath()+" with success code "+result);
	}

	private void triggerOnChangeCallback() {
		if(onChangeCallback!=null)
			onChangeCallback.accept(this);
	}

	private String discoverYdlBinary() {
		return ydlDiscoverer.discoverYdlBinary();
	}

	public static YdlDownloadTaskBuilder builder(){
		return new YdlDownloadTaskBuilder();
	}

	public YdlDownloadState getDownloadState() {
		return downloadState.get();
	}

	public static class YdlDownloadTaskBuilder {
		private String url;
		private Consumer<YdlDownloadTask> onChangeCallback;
		private String outputFolder=".";

		public YdlDownloadTaskBuilder setUrl(String url) {
			this.url = url;
			return this;
		}

		public YdlDownloadTask build() {
			return new YdlDownloadTask(url, outputFolder, onChangeCallback);
		}

		public YdlDownloadTaskBuilder setOnChangeCallback(Consumer<YdlDownloadTask> onChangeCallback) {
			this.onChangeCallback = onChangeCallback;
			return this;
		}

		public YdlDownloadTaskBuilder setOutputFolder(String outputFolder) {
			this.outputFolder = outputFolder;
			return this;
		}
	}


}
