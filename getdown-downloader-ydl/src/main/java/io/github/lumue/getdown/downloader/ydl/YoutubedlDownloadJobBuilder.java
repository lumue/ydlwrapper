package io.github.lumue.getdown.downloader.ydl;

import io.github.lumue.getdown.core.download.job.AbstractDownloadJob;
import io.github.lumue.getdown.core.download.job.DownloadJob;
import io.github.lumue.getdown.core.download.task.DownloadTask;
import org.springframework.stereotype.Component;


class YoutubedlDownloadJobBuilder
        extends AbstractDownloadJob.AbstractDownloadJobBuilder {

    public YoutubedlDownloadJobBuilder(DownloadTask downloadTask) {
        super(downloadTask);
    }

    @Override
    public DownloadJob build() {
        return new YoutubedlDownloadJob(this.handle,
                this.name,
                this.url,
                this.host,
                this.options,
                this.downloadPath,
                this.index,
                this.targetPath,
                this.downloadTask);
    }

}
