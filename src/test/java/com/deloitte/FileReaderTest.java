package com.deloitte;

import org.junit.Test;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
public class FileReaderTest {

    @Test
    public void testReadFromFile_Success() throws FileNotFoundException {
        FileReader fileReader = new FileReader();
        List<String> lines = new ArrayList<>();

        fileReader.readFromFile("src/test/test_resources/AddressBook.txt", lines::add);

        assertEquals(5, lines.size());
    }

    @Test
    public void testReadFromFile_ContentSuccess() throws FileNotFoundException {
        FileReader fileReader = new FileReader();
        List<String> lines = new ArrayList<>();

        fileReader.readFromFile("src/test/test_resources/AddressBook.txt", lines::add);

        assertEquals("Bill McKnight, Male, 16/03/77", lines.get(0));
    }

    @Test
    public void testReadFromFile_FileNotFoundException() {
        FileReader fileReader = new FileReader();

        assertThrows(FileNotFoundException.class, () -> fileReader.readFromFile("non_existing_file.txt", System.out::println));
    }

}
