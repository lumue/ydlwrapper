package io.github.lumue.ydlwrapper.metadata.single_info_json;

import io.github.lumue.ydlwrapper.metadata.statusmessage.YdlDownloadMetadataAccessor;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Created by lm on 12.03.16.
 */
public class SingleInfoJsonDownloadMetadataAccessor implements YdlDownloadMetadataAccessor {

	private final YdlInfoJson ydlInfoJson;

	public SingleInfoJsonDownloadMetadataAccessor(YdlInfoJson ydlInfoJson) {
		this.ydlInfoJson=Objects.requireNonNull(ydlInfoJson);
	}

	private final static Logger LOGGER= LoggerFactory.getLogger(SingleInfoJsonDownloadMetadataAccessor.class);

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
		}catch(Throwable t){
			LOGGER.warn("could not get filesize ",t);
			filesize=null;
		}
		return filesize!=null?Optional.of(filesize):Optional.empty();
	}

	private Long getFilesizeFromMergedFormat(String formatId) {
		return ydlInfoJson.getRequestedFormats().stream()
				.filter((requestedFormat -> formatId.equals(requestedFormat.getFormatId())))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("format not found"))
				.getFilesize()
				.longValue();
	}

	private Long getFilesizeFromPlaylist(String filename, String formatId) {
		Entry entry=getPlaylistEntryByFilename(filename);

		if(isMergedFormat(entry)){
			return entry.getRequestedFormats().stream()
					.filter((requestedFormat -> formatId.equals(requestedFormat.getFormatId())))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("format not found"))
					.getFilesize()
					.longValue();
		}

		return entry.getFormats().stream()
				.filter((requestedFormat -> formatId.equals(requestedFormat.getFormatId())))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("format not found"))
				.getFilesize()
				.longValue();
	}

	private boolean isMergedFormat(Entry entry) {
		List<RequestedFormat> requestedFormats = entry.getRequestedFormats();
		return requestedFormats!=null&&!requestedFormats.isEmpty();
	}

	private Entry getPlaylistEntryByFilename(String filename) {
		Objects.requireNonNull(filename);
		return ydlInfoJson.getEntries().stream()
				.filter((entry -> filename.contains(entry.getId())||filename.contains(entry.getTitle())))
				.findFirst()
				.orElse(null);
	}

	@Override
	public boolean isPlaylist() {
		String type = ydlInfoJson.getType();
		return type!=null&&type.equals("playlist")&&ydlInfoJson.getEntries()!=null;
	}

	public boolean isMergedFormat() {
		List<RequestedFormat> requestedFormats = ydlInfoJson.getRequestedFormats();
		return requestedFormats!=null&&!requestedFormats.isEmpty();
	}

	public Long getFilesizeFromSingleFileDownload(String formatId) {
		Integer filesize = ydlInfoJson.getFormats().stream()
				.filter(requestedFormat -> formatId.equals(requestedFormat.getFormatId()))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("format not found"))
				.getFilesize();

		if(filesize!=null)
			return filesize.longValue();
		else
			return null;
	}

	;
}
