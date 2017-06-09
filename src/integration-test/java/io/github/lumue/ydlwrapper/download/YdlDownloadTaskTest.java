package io.github.lumue.ydlwrapper.download;

import org.junit.After;
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

import static io.github.lumue.ydlwrapper.download.YdlDownloadTask.YdlDownloadState.ERROR;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the {@link YdlDownloadTask}
 * Created by lm on 06.03.16.
 */
public class YdlDownloadTaskTest {

	private static final String BASE_TEST_OUTPUT = "build/testoutput/";
	private static final String OUTPUT_FOLDER = BASE_TEST_OUTPUT + YdlDownloadTaskTest.class.getSimpleName();
	private YdlDownloadTask downloadTask;


	private int outputFileChangedCount=0;

	private int newOutputFileCount=0;

	private int cancelCount=0;
	@Before
	public void setUp() throws IOException {
		prepareOutputFolder();
		outputFileChangedCount=0;
		newOutputFileCount=0;
		cancelCount=0;
		downloadTask=YdlDownloadTask.builder()
				.setUrl("https://www.youtube.com/watch?v=nwP80FmSpOw")
				.setOutputFolder(OUTPUT_FOLDER)
				.setWriteInfoJson(true)
				.onNewOutputFile((a,b)->newOutputFileCount++)
				.onOutputFileChange((a,b)->outputFileChangedCount++)
				.onCancel((a,b) -> cancelCount++)
				.setForceMp4(true)
				.build();

	}

	private void prepareOutputFolder() throws IOException {
		File outputFolder=new File(OUTPUT_FOLDER);

		if(!outputFolder.isDirectory()){
			if(outputFolder.exists())
				outputFolder.delete();
			Files.createDirectory(new File(BASE_TEST_OUTPUT).toPath());
			Files.createDirectory(outputFolder.toPath());
		}

		 Optional.ofNullable(outputFolder.listFiles()).ifPresent(
				files -> Arrays.stream(files).forEach(File::delete)
		);
	}

	@Test
	public void prepare() throws Exception {
		assertFalse(downloadTask.getYdlDownloadTaskMetadata().isPresent());
		downloadTask.prepare();
		assertTrue(downloadTask.getYdlDownloadTaskMetadata().isPresent());
	}

	@Test
	public void execute() throws Exception {
		downloadTask.execute();
		assertFalse(ERROR.equals(downloadTask.getDownloadState()));
		Stream<Path> files = Files.list(new File(OUTPUT_FOLDER).toPath());
		assertFalse(files==null || files.count()<1);
	}

	@Test
	public void executeAndCancelImmediately() throws Exception {
		downloadTask.executeAsync();
		downloadTask.cancel();
		Assert.assertEquals(YdlDownloadTask.YdlDownloadState.CANCELED,downloadTask.getDownloadState());
		Assert.assertEquals(1,cancelCount);
	}

	@After
	public  void tearDown(){

	}
}