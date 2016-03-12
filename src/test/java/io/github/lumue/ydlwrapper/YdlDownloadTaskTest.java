package io.github.lumue.ydlwrapper;

import io.github.lumue.ydlwrapper.download.YdlDownloadTask;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * Test the {@link YdlDownloadTask}
 * Created by lm on 06.03.16.
 */
public class YdlDownloadTaskTest {

	private static final String BASE_TEST_OUTPUT = "build/testoutput/";
	private static final String OUTPUT_FOLDER = BASE_TEST_OUTPUT + YdlDownloadTaskTest.class.getSimpleName();
	private YdlDownloadTask downloadTask;

	@Before
	public void setUp() throws IOException {
		prepareOutputFolder();
		downloadTask=YdlDownloadTask.builder()
				.setUrl("https://www.youtube.com/watch?v=79TRDRPGx34")
				.setOutputFolder(OUTPUT_FOLDER)
				.onNewOutputFile((a,b)-> System.out.println("now writing to "+b.getAbsolutePath()))
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

		for (File file:outputFolder.listFiles()){
			file.delete();
		}
	}

	@Test
	public void execute() throws Exception {
		downloadTask.execute();
		Stream<Path> files = Files.list(new File(OUTPUT_FOLDER).toPath());
		Assert.assertFalse(files==null || files.count()<1);
	}

	@After
	public  void tearDown(){

	}
}