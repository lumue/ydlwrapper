package io.github.lumue.ydlwrapper.metadata;

import io.github.lumue.ydlwrapper.metadata.statusmessage.NewDownloadStatusMessage;
import org.junit.Test;

import java.util.function.Supplier;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by lm on 12.03.16.
 */
public class NewDownloadStatusMessageTest {

	private final static String VALID_MESSAGE="[download] Destination: \"Watch How John Oliver Describes Countries (Compilation).f22.mp4\"";
	private static final String EXPECTED_FORMAT = "22";
	private static final String EXPECTED_FILENAME="\"Watch How John Oliver Describes Countries (Compilation).f22.mp4\"";
	private static final String EXPECTED_EXTENSION="mp4";

	@Test
	public void parseFormatGivenValidMessage() throws Exception {
		NewDownloadStatusMessage message = new NewDownloadStatusMessage(VALID_MESSAGE);
		assertParseResult(message,EXPECTED_FORMAT,message::getFormatId);
	}

	@Test
	public void parseFilenameGivenValidMessage() throws Exception {
		NewDownloadStatusMessage message = new NewDownloadStatusMessage(VALID_MESSAGE);
		assertParseResult(message,EXPECTED_FILENAME,message::getFilename);
	}

	@Test
	public void parseExtensionGivenValidMessage() throws Exception {
		NewDownloadStatusMessage message = new NewDownloadStatusMessage(VALID_MESSAGE);
		assertParseResult(message,EXPECTED_EXTENSION,message::getExtension);
	}

	private void assertParseResult(NewDownloadStatusMessage message,String expectedResult,Supplier<String> actualSupplier) {
		String actual = actualSupplier.get();
		assertThat("parsed formatId matches "+EXPECTED_FORMAT, actual,equalTo(expectedResult));
	}


}