package io.github.lumue.ydlwrapper.download;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Test the {@link YdlDownloadTask}
 * Created by lm on 06.03.16.
 */
public class YdlDownloadTaskTest {

	private static final Logger LOGGER= LoggerFactory.getLogger(YdlDownloadTaskTest.class);

	private static final String BASE_TEST_OUTPUT = "build/testoutput/";
	private static final String OUTPUT_FOLDER = BASE_TEST_OUTPUT + YdlDownloadTaskTest.class.getSimpleName();
	private YdlDownloadTask downloadTask;



	private int cancelCount=0;
	@Before
	public void setUp() throws IOException {
		prepareOutputFolder();
		cancelCount=0;
		downloadTask=YdlDownloadTask.builder()
				.setUrl("https://www.youtube.com/watch?v=nwP80FmSpOw")
				.setOutputFolder(OUTPUT_FOLDER)
				.setWriteInfoJson(true)
				.onOutputFileChange((a,b)-> LOGGER.debug("output file changed callback ({},{})",a,b))
				.onCancel((a,b) -> cancelCount++)
				.onStateChanged((a,b)-> LOGGER.debug("state changed callback ({},{})",a,b))
				.setForceMp4(true)
				.build();

	}

	private void prepareOutputFolder() throws IOException {
		File outputFolder=new File(OUTPUT_FOLDER);

		if(!outputFolder.isDirectory()){
			if(outputFolder.exists())
				Files.delete(outputFolder.toPath());
			Files.createDirectory(new File(BASE_TEST_OUTPUT).toPath());
			Files.createDirectory(outputFolder.toPath());
		}

		 Optional.ofNullable(outputFolder.listFiles()).ifPresent(
				files -> Arrays.stream(files).forEach(File::delete)
		);
	}

	@Test
	public void prepare()  {
		assertFalse(downloadTask.getYdlDownloadTaskMetadata().isPresent());
		downloadTask.prepare();
		assertTrue(downloadTask.getYdlDownloadTaskMetadata().isPresent());
	}

	@Test
	public void execute() throws IOException {

		downloadTask.execute();

		assertEquals(YdlDownloadTask.YdlDownloadState.SUCCESS, downloadTask.getDownloadState());

		try(Stream<Path> files = Files.list(new File(OUTPUT_FOLDER).toPath())) {
            assertTrue(files.findAny().isPresent());
		}


	}

	@Test
	public void executeAndCancelImmediately() {
		downloadTask.executeAsync();
		downloadTask.cancel();
		Assert.assertEquals(YdlDownloadTask.YdlDownloadState.CANCELED,downloadTask.getDownloadState());
		Assert.assertEquals(1,cancelCount);
	}

}