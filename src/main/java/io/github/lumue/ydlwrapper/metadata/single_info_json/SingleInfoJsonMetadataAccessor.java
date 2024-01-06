package io.github.lumue.ydlwrapper.metadata.single_info_json;


import io.github.lumue.ydlwrapper.metadata.DocumentIdMetadataAccessor;
import io.github.lumue.ydlwrapper.metadata.ExpectedFilesizeMetadataAccessor;
import io.github.lumue.ydlwrapper.metadata.ExtractorMetadataAccessor;
import io.github.lumue.ydlwrapper.metadata.TitleMetadataAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


/**
 * Created by lm on 12.03.16.
 */
public class SingleInfoJsonMetadataAccessor
        implements ExpectedFilesizeMetadataAccessor,
        TitleMetadataAccessor,
        DocumentIdMetadataAccessor,
        ExtractorMetadataAccessor {

    private final YdlInfoJson ydlInfoJson;

    public SingleInfoJsonMetadataAccessor(YdlInfoJson ydlInfoJson) {
        this.ydlInfoJson = Objects.requireNonNull(ydlInfoJson);
    }

    private final static Logger LOGGER = LoggerFactory.getLogger(SingleInfoJsonMetadataAccessor.class);

    @Override
    public Optional<Long> getFilesize(String filename, String formatId) {
        Long filesize;
        try {
            if (isPlaylist()) {
                filesize = getFilesizeFromPlaylist(filename, formatId);
            } else if (isMergedFormat()) {
                filesize = getFilesizeFromMergedFormat(formatId);
            } else
                filesize = getFilesizeFromSingleFileDownload(formatId);
        } catch (Throwable t) {
            LOGGER.warn("could not get filesize ", t);
            filesize = null;
        }
        return filesize != null ? Optional.of(filesize) : Optional.empty();
    }

    private Long getFilesizeFromMergedFormat(String formatId) {
        Integer filesize = ydlInfoJson.getRequestedFormats().stream()
                .filter((requestedFormat -> formatId.equals(requestedFormat.getFormatId())))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("format not found"))
                .getFilesize();
        return filesize != null ? filesize.longValue() : null;
    }

    private Long getFilesizeFromPlaylist(String filename, String formatId) {
        Entry entry = getPlaylistEntryByFilename(filename);
        Integer filesize = null;

        if (isMergedFormat(entry)) {
            filesize = entry.getRequestedFormats().stream()
                    .filter((requestedFormat -> formatId.equals(requestedFormat.getFormatId())))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("format not found"))
                    .getFilesize();

        } else {
            filesize = entry.getFormats().stream()
                    .filter((requestedFormat -> formatId.equals(requestedFormat.getFormatId())))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("format not found"))
                    .getFilesize();
        }
        return filesize != null ? filesize.longValue() : null;
    }

    private boolean isMergedFormat(Entry entry) {
        List<RequestedFormat> requestedFormats = entry.getRequestedFormats();
        return requestedFormats != null && !requestedFormats.isEmpty();
    }

    private Entry getPlaylistEntryByFilename(String filename) {
        Objects.requireNonNull(filename);
        return ydlInfoJson.getEntries().stream()
                .filter((entry -> filename.contains(entry.getId()) || filename.contains(entry.getTitle())))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean isPlaylist() {
        String type = ydlInfoJson.getType();
        return type != null && type.equals("playlist") && ydlInfoJson.getEntries() != null;
    }

    private boolean isMergedFormat() {
        List<RequestedFormat> requestedFormats = ydlInfoJson.getRequestedFormats();
        return requestedFormats != null && !requestedFormats.isEmpty();
    }

    private Long getFilesizeFromSingleFileDownload(String formatId) {
        Integer filesize = ydlInfoJson.getFormats().stream()
                .filter(requestedFormat -> formatId.equals(requestedFormat.getFormatId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("format not found"))
                .getFilesize();

        if (filesize != null)
            return filesize.longValue();
        else
            return null;
    }

    @Override
    public Optional<String> getDocumentId() {
        String id = ydlInfoJson.getId();
        return Optional.ofNullable(id);
    }

    @Override
    public Optional<String> getExtractor() {
        String id = ydlInfoJson.getExtractor();
        return Optional.ofNullable(id);
    }

    @Override
    public Optional<String> getTitle() {
        String id = ydlInfoJson.getTitle();
        return Optional.ofNullable(id);
    }

}
