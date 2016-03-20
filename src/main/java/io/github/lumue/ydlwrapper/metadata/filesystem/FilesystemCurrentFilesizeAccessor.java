package io.github.lumue.ydlwrapper.metadata.filesystem;

import io.github.lumue.ydlwrapper.download.YdlFileDownload;
import io.github.lumue.ydlwrapper.metadata.CurrentFilesizeMetadataAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

/**
 * Created by lm on 18.03.16.
 */
public class FilesystemCurrentFilesizeAccessor implements CurrentFilesizeMetadataAccessor {

	private final static Logger LOGGER= LoggerFactory.getLogger(FilesystemCurrentFilesizeAccessor.class);


	@Override
	public Optional<Long> getFilesize(YdlFileDownload fileDownload) {
		File tempout = new File(fileDownload.getAbsoluteTempoutFilename());
		File finalout = new File(fileDownload.getAbsoluteOutFilename());
		Long size=null;
		size=getSize(finalout);
		if(size==null){
			size=getSize(tempout);
		}
		if(size==null)
			LOGGER.error("could not determine filesize of current download");
		return Optional.ofNullable(size);
	}

	private Long getSize(File file) {
		Long size=null;
		if(file.exists()) {
			try {
				size = Files.size(file.toPath());
			} catch (IOException e) {
				size = null;
			}
		}
		return size;
	}
}
