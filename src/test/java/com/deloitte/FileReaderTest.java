package com.deloitte;

import com.deloitte.config.Configuration;
import org.junit.Before;
import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
public class FileReaderTest {

    private String testFilePath;

    @Before
    public void setUp() {
        //Use of a config variable to have one common variable for each Test class, and possibility to be different for each environment if needed
        testFilePath = Configuration.getProperty("testFilePath");
    }
    @Test
    public void testReadFromFile_Success() throws FileNotFoundException {
        FileReader fileReader = new FileReader();
        List<String> lines = new ArrayList<>();
        fileReader.readFromFile(testFilePath, lines::add);
        assertEquals(5, lines.size());
    }

    @Test
    public void testReadFromFile_ContentSuccess() throws FileNotFoundException {
        FileReader fileReader = new FileReader();
        List<String> lines = new ArrayList<>();
        fileReader.readFromFile(testFilePath, lines::add);
        assertEquals("Bill McKnight, Male, 16/03/77", lines.get(0));
    }

    @Test
    public void testReadFromFile_FileNotFoundException() {
        FileReader fileReader = new FileReader();
        assertThrows(FileNotFoundException.class, () -> fileReader.readFromFile("non_existing_file.txt", System.out::println));
    }

}
