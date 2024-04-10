package io.github.lumue.getdown.core.download;

import io.github.lumue.getdown.core.download.job.DownloadJob;
import io.github.lumue.getdown.core.download.task.DownloadTask;

public interface DownloadJobFactory {
    DownloadJob create(DownloadTask task);
}
