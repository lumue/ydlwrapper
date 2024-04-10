package io.github.lumue.getdown.downloader.ydl;

import io.github.lumue.getdown.core.download.ValidateTaskJobFactory;
import io.github.lumue.getdown.core.download.job.ValidateTaskJob;
import io.github.lumue.getdown.core.download.task.DownloadTask;
import org.springframework.stereotype.Component;

@Component
public class YoutubedlValidateTaskJobFactory implements ValidateTaskJobFactory {
    @Override
    public ValidateTaskJob create(DownloadTask downloadTask) {
        return new YoutubedlValidateJob(downloadTask);
    }
}
