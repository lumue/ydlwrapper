package io.github.lumue.getdown.webapp.webapi;

import io.github.lumue.getdown.core.download.job.DownloadJob;
import io.github.lumue.getdown.core.download.job.DownloadProgress;

import java.io.Serializable;
import java.util.Optional;

public interface DownloadJobView extends Serializable {
	
	String getHandle();
	String getName();
	String getUrl();
	String getState();
	Long getSize();
	Long getProgress();
	String getMessage();
	
	static DownloadJobView wrap(final DownloadJob download)
	{
		return new DownloadJobView() {

			private static final long serialVersionUID = 2033910253254112701L;

			@Override
			public String getName() {
				String outputFilename = download.getName()!=null?download.getName():download.getUrl();
				return outputFilename.substring(outputFilename.lastIndexOf('/') + 1);
			}

			@Override
			public String getUrl() {
				return download.getUrl();
			}

			@Override
			public Long getSize() {

				Optional<DownloadProgress> downloadProgress = download.getDownloadProgress();
				if (!downloadProgress.isPresent())
					return 1L;

				return downloadProgress.get().getSize();

			}

			@Override
			public Long getProgress() {
				Optional<DownloadProgress> downloadProgress = download.getDownloadProgress();
				if (!downloadProgress.isPresent())
					return 1L;

				return downloadProgress.get().getDownloadedSize();
			}

			@Override
			public String getHandle() {
				return download.getHandle();
			}

			@Override
			public String getState() {
				return download.getState().name();
			}

			@Override
			public String getMessage() {
				return download.getMessage().orElse("");
			}
			
		};
	}
}
