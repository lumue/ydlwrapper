package io.github.lumue.getdown.core.download;

import io.github.lumue.getdown.core.download.job.ValidateTaskJob;
import io.github.lumue.getdown.core.download.task.DownloadTask;

public interface ValidateTaskJobFactory {


    ValidateTaskJob create(DownloadTask downloadTask);

}
