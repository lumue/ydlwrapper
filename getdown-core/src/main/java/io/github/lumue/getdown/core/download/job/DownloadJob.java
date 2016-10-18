package io.github.lumue.getdown.core.download.job;

import java.io.Serializable;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.github.lumue.getdown.core.common.persistence.HasIdentity;
import io.github.lumue.getdown.core.common.util.Observable;
import io.github.lumue.getdown.core.download.job.Download.DownloadJobHandle;

@JsonTypeInfo(use=JsonTypeInfo.Id.CLASS, include=JsonTypeInfo.As.PROPERTY, property="@class")
public interface DownloadJob extends HasIdentity<DownloadJobHandle>,Serializable,Observable,Runnable {

	@Override
	DownloadJobHandle getHandle();

	Optional<Throwable> getError();

	Optional<String> getMessage();

	Optional<DownloadProgress> getDownloadProgress();

	DownloadJobState getState();

	String getOutputFilename();

	String getUrl();

	String getHost();

	String getName();



	void setDownloadPath(String downloadPath);

	void prepare();

	void run();


	void cancel();


	/**
	 * sort order
	 * @return
	 */
	Long getIndex();

	boolean isPrepared();

	void waiting();

	enum DownloadJobState {
		WAITING,PREPARING,RUNNING, ERROR, FINISHED, CANCELLED, PREPARED;
	}
}
