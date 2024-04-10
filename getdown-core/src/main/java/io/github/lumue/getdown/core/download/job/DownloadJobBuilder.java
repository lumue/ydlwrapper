package io.github.lumue.getdown.core.download.job;

import io.github.lumue.getdown.core.common.persistence.ObjectBuilder;

import java.util.Collection;


public interface DownloadJobBuilder extends ObjectBuilder<DownloadJob> {
    AbstractDownloadJob.AbstractDownloadJobBuilder withIndex(long index);

    AbstractDownloadJob.AbstractDownloadJobBuilder withOptions(Collection<String> options);

    AbstractDownloadJob.AbstractDownloadJobBuilder withName(String name);

    AbstractDownloadJob.AbstractDownloadJobBuilder withUrl(String url);

    @Override
    AbstractDownloadJob.AbstractDownloadJobBuilder withHandle(String keyValue);

    @Override
    boolean hasHandle();

    AbstractDownloadJob.AbstractDownloadJobBuilder withDownloadPath(String downloadPath);

    AbstractDownloadJob.AbstractDownloadJobBuilder withTargetPath(String targetPath);
}
