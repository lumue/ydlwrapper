package io.github.lumue.getdown.downloader.ydl;

import io.github.lumue.getdown.core.download.DownloadJobFactory;
import io.github.lumue.getdown.core.download.job.DownloadJob;
import io.github.lumue.getdown.core.download.task.DownloadTask;
import org.springframework.stereotype.Component;

@Component
public class YoutubedlDownloadJobFactory implements DownloadJobFactory {
    @Override
    public DownloadJob create(DownloadTask task) {
        return YoutubedlDownloadJob.builder(task).build();
    }
}
