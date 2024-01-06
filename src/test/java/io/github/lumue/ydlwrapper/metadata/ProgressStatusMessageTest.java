package io.github.lumue.ydlwrapper.metadata;

import io.github.lumue.ydlwrapper.metadata.statusmessage.ProgressStatusMessage;
import org.junit.Test;

import java.math.BigDecimal;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by lm on 12.03.16.
 */
public class ProgressStatusMessageTest {


    private final static String VALID_MESSAGE = "[download]  65.6% of 137.19MiB at  1.04MiB/s ETA 00:45";
    private static final BigDecimal EXPECTED_PERCENTAGE = new BigDecimal("65.6");
    private static final long EXPECTED_SIZE = new BigDecimal("137.19").multiply(new BigDecimal(1024 * 1024)).longValue();

    @Test
    public void parsePercentageGivenValidMessage() throws Exception {
        ProgressStatusMessage message = new ProgressStatusMessage(VALID_MESSAGE);
        assertThat("parsed percentage matches expectation", message.getPercentage(), equalTo(EXPECTED_PERCENTAGE));
    }


    @Test
    public void parseSizeGivenValidMessage() throws Exception {
        ProgressStatusMessage message = new ProgressStatusMessage(VALID_MESSAGE);
        assertThat("parsed percentage matches expectation", message.getExpectedSize(), equalTo(EXPECTED_SIZE));
    }


}