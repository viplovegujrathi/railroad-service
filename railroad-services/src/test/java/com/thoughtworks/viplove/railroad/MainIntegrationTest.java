package com.thoughtworks.viplove.railroad;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import org.junit.Test;


/**
 * Integration tests to prone for bugs in the interaction of the internal components through end-to-end tests.
 * @author vigujrat
 *
 */
public class MainIntegrationTest {

    private static final String[] inputFiles = {"/input-1.txt", "/input-2.txt"/*, "/input-3.txt"*/};
    private static final String[] outputFiles = {"/output-1.txt", "/output-2.txt"/*, "/output-3.txt"*/};

    @Test
    public void shouldProcessRailBIReportCorrectly() throws FileNotFoundException {

        for (int testFilesPair = 0; testFilesPair < inputFiles.length; testFilesPair++)
            testProcessRailBIReportForGivenInOutFiles(inputFiles[testFilesPair], outputFiles[testFilesPair]);
    }

    private void testProcessRailBIReportForGivenInOutFiles(
            final String inputFilePath,
            final String outputFilePath)
                    throws FileNotFoundException {

        // given
        final URL inputURL = this.getClass().getResource(inputFilePath);
        final Application subject = new Application();

        // when
        final List<String> answers = subject.processInput(new String[] {inputURL.getFile()});

        // then
        final URL outputURL = this.getClass().getResource(outputFilePath);
        final Scanner outputScanner = new Scanner(new FileInputStream(outputURL.getFile()));

        for (final String answer : answers) {
            assertEquals(outputScanner.nextLine(), answer);
        }
        outputScanner.close();
    }

    @Test(expected=IllegalArgumentException.class)
    public void shouldThrowExceptionWithInvalidFile() throws FileNotFoundException {
        // given
        final Application subject = new Application();

        // when then
        subject.processInput(new String[] {"input-fake.txt"});
    }

}
