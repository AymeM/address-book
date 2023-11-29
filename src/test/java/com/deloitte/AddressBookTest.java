package com.deloitte;

import com.deloitte.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import java.util.List;

import com.deloitte.config.Configuration;

import static org.junit.Assert.*;

//TODO Use Mockito instead of reading the file
public class AddressBookTest {

    private AddressBook addressBook;
    private String testFilePath;

    @Before
    public void setUp() {
        //Use of a config variable to have one common variable for each Test class, and possibility to be different for each environment if needed
        testFilePath = Configuration.getProperty("testFilePath");
        addressBook = new AddressBook();

    }

    // FILE OPERATIONS TESTING ////////////////////////////////////////////////////////////////////////////////////
    @Test
    public void testReadAddressBookFromFile() {
        addressBook.readAddressBookFromFile(testFilePath);
        List<Person> people = addressBook.getAddressBook();

        assertEquals(5, people.size());
        assertPersonDetails(people.get(0), "Bill McKnight", "Male", DateUtil.parseDate("16/03/77"));
        assertPersonDetails(people.get(1), "Paul Robinson", "Male", DateUtil.parseDate("15/01/85"));
        assertPersonDetails(people.get(2), "Gemma Lane", "Female", DateUtil.parseDate("20/11/91"));
        assertPersonDetails(people.get(3), "Sarah Stone", "Female", DateUtil.parseDate("20/09/80"));
        assertPersonDetails(people.get(4), "Wes Jackson", "Male", DateUtil.parseDate("14/08/74"));
    }

    @Test
    public void testReadEmptyAddressBookFromFile() {
        String emptyTestFilePath = Configuration.getProperty("emptyTestFilePath");
        addressBook.readAddressBookFromFile(emptyTestFilePath);
        assertTrue(addressBook.getAddressBook().isEmpty());
    }

    @Test(expected = RuntimeException.class)
    public void testReadFromInvalidFilePath() {
        String invalidTestFilePath = "invalid/path/AddressBook.txt";
        addressBook.readAddressBookFromFile(invalidTestFilePath);
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void assertPersonDetails(Person person, String name, String gender, Date birthDate) {
        assertEquals(name, person.getName());
        assertEquals(gender, person.getGender());
        assertEquals(birthDate, person.getBirthDate());
    }

    @Test
    public void testCountMaleSuccess() {
        addressBook.readAddressBookFromFile(testFilePath);
        assertEquals(3, addressBook.countMales());
    }

    @Test
    public void testFindOldPersonSuccess() {
        addressBook.readAddressBookFromFile(testFilePath);
        assertEquals("Wes Jackson", addressBook.findOldestPerson().getName());
    }

    @Test
    public void testFindOldestPersonInEmptyAddressBook() {
        String emptyTestFilePath = Configuration.getProperty("emptyTestFilePath");
        addressBook.readAddressBookFromFile(emptyTestFilePath);
        assertNull(addressBook.findOldestPerson());
    }

    @Test
    public void testFindAgeDifference() {
        addressBook.readAddressBookFromFile(testFilePath);
        assertEquals(2862, addressBook.calculateAgeDifferenceInDays("Bill McKnight", "Paul Robinson"));
    }

    @Test
    public void testFindAgeDifferenceWithNonExistentNames() {
        addressBook.readAddressBookFromFile(testFilePath);
        int result = addressBook.calculateAgeDifferenceInDays("Nonexistent Name 1", "Nonexistent Name 2");
        assertEquals(-1, result);
    }


}
