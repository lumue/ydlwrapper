package io.github.lumue.ydlwrapper.metadata.single_info_json;

import io.github.lumue.ydlwrapper.metadata.YdlDownloadMetadataAccessor;

import java.util.List;
import java.util.Objects;

/**
 * Created by lm on 12.03.16.
 */
public class SingleInfoJsonDownloadMetadataAccessor implements YdlDownloadMetadataAccessor {

	private final YdlInfoJson ydlInfoJson;

	public SingleInfoJsonDownloadMetadataAccessor(YdlInfoJson ydlInfoJson) {
		this.ydlInfoJson=ydlInfoJson;
	}

	@Override
	public Long getFilesize(String filename, String formatExtension) {
		if(isPlaylist()){
			return getFilesizeFromPlaylist(filename,formatExtension);
		}else if(isMergedFormat()){
			return getFilesizeFromMergedFormat(filename,formatExtension);
		}
		return getFilesizeFromSingleFileDownload();
	}

	private Long getFilesizeFromMergedFormat(String filename, String formatExtension) {
		return ydlInfoJson.getRequestedFormats().stream()
				.filter((requestedFormat -> formatExtension.equals(requestedFormat.getExt())))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("format not found"))
				.getFilesize()
				.longValue();
	}

	private Long getFilesizeFromPlaylist(String filename, String formatExtension) {
		Entry entry=getPlaylistEntryByFilename(filename);

		if(isMergedFormat(entry)){
			return entry.getRequestedFormats().stream()
					.filter((requestedFormat -> formatExtension.equals(requestedFormat.getExt())))
					.findFirst()
					.orElseThrow(() -> new RuntimeException("format not found"))
					.getFilesize()
					.longValue();
		}
		String formatId=entry.getFormatId();
		return entry.getFormats().stream()
				.filter((requestedFormat -> formatId.equals(requestedFormat.getFormat())))
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

	public Long getFilesizeFromSingleFileDownload() {
		String ext=ydlInfoJson.getFormat();
		Format format = ydlInfoJson.getFormats().stream()
				.filter(requestedFormat -> ext.equals(requestedFormat.getFormat()))
				.findFirst()
				.orElseThrow(() -> new RuntimeException("format not found"));
		return format
				.getFilesize()
				.longValue();
	}

	;
}
