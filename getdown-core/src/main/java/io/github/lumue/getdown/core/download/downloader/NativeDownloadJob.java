package io.github.lumue.getdown.core.download.downloader;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lumue.getdown.core.common.util.Observer;
import io.github.lumue.getdown.core.download.job.AbstractDownloadJob;
import io.github.lumue.getdown.core.download.job.DownloadJob;
import io.github.lumue.getdown.core.download.job.DownloadProgress;
import io.github.lumue.getdown.core.download.job.Progression;
import io.github.lumue.getdown.core.download.task.DownloadTask;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.function.Consumer;

public class NativeDownloadJob extends AbstractDownloadJob {
	
	
	private final Consumer<Progression> progressionListener = (p) -> getDownloadProgress().ifPresent(dp ->
	{
		dp.setSize(p.getMax());
		dp.updateDownloadedSize(p.getValue());
		progress(dp);
		message(message);
	});
	
	private final Observer<DownloadFilesStep> progressionObserver= observable -> progressionListener.accept(observable.getProgression());
	
	@JsonCreator
	private NativeDownloadJob(
			@JsonProperty("url") String url,
			@JsonProperty("handle") String handle,
			@JsonProperty("state") DownloadJob.DownloadJobState downloadJobState,
			@JsonProperty("downloadProgress") DownloadProgress downloadProgress,
			@JsonProperty("name") String name,
			@JsonProperty("host") String host,
			@JsonProperty("index") Long index,
			@JsonProperty("targetPath") String targetPath,
			@JsonProperty("downloadTask") DownloadTask downloadTask,
			@JsonProperty("downloadPath") String downloadPath) {
		super(url, handle, downloadJobState, downloadProgress, name, host, index, targetPath, downloadTask, downloadPath);
	}
	
	private NativeDownloadJob(String name, String url, String host, String handle, Long index, String targetPath, DownloadTask downloadTask, String downloadPath) {
		super(name, url, host, handle, index, targetPath, downloadTask, downloadPath);
	}
	
	@Override
	public void prepare() {
		preparing();
		prepared();
	}
	
	
	
	@Override
	public void executeDownload() {
		progress(new DownloadProgress());
		start();
		getDownloadProgress().ifPresent(dp -> {
			try {
				dp.start();
				message("downloading...");
				final DownloadFilesStep downloadFilesStep = new DownloadFilesStep(
						getDownloadTask().getSelectedFormats(),
						getWorkPath()
				);
				downloadFilesStep.addObserver(progressionObserver);
				downloadFilesStep.execute();
				
				final String ouputfilename = "MERGED." + getDownloadTask().getExt();
				
				if (getDownloadTask().getSelectedFormats().size() == 2) {
					message("merging...");
					final MergeFilesStep mergeFilesStep = new MergeFilesStep(
							getDownloadTask().getSelectedFormats(),
							getWorkPath(),
							ouputfilename
					);
					mergeFilesStep.addObserver(progressionObserver);
					mergeFilesStep.execute();
					
					getDownloadTask().getSelectedFormats().forEach(f -> {
						FileUtils.deleteQuietly(new File(getWorkPath() + File.separator + f.getFilename()));
					});
				}
				
				
				final String finalFilename = getWorkPath() + File.separator + getDownloadTask().getName() + "." + getDownloadTask().getExt();
				final File finalFile = new File(finalFilename);
				if (finalFile.exists())
					FileUtils.deleteQuietly(finalFile);
				FileUtils.moveFile(
						new File(getWorkPath() + File.separator + ouputfilename),
						finalFile
				);
				
				
				message("writing info.json");
				
				FileUtils.writeStringToFile(new File(getWorkPath() + File.separator + getDownloadTask().getName() + ".info.json"), getDownloadTask().getInfoJsonString(), Charset.forName("UTF-8"));
				
				dp.finish();
				
			} catch (IOException e) {
				error(e);
				dp.error(e);
			}
		});
		
		
	}
	
	
	@Override
	public void cancel() {
	
	}
	
	@Override
	public boolean isPrepared() {
		return DownloadJobState.PREPARED.equals(getState());
	}
	
	public static NativeDownloadJobJobBuilder builder(DownloadTask downloadTask) {
		return new NativeDownloadJobJobBuilder(downloadTask);
	}
	
	public static class NativeDownloadJobJobBuilder
			extends AbstractDownloadJobBuilder {
		
		public NativeDownloadJobJobBuilder(DownloadTask downloadTask) {
			super(downloadTask);
		}
		
		@Override
		public DownloadJob build() {
			return new NativeDownloadJob(this.name, this.url, this.host, this.handle, this.index, this.targetPath, this.downloadTask, this.downloadPath);
		}
		
	}
}
