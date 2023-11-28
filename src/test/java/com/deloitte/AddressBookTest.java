package com.deloitte;

import org.junit.Before;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class AddressBookTest {

    private AddressBook addressBook;

    @Before
    public void setUp() {
        addressBook = new AddressBook();

    }

    @Test
    public void testReadAddressBookFromFile() {
        //Local variable in case there is a need to test each function with a different file
        String testFilePath = "src/test/test_resources/AddressBook.txt";
        addressBook.readAddressBookFromFile(testFilePath);

        List<Person> people = addressBook.getAddressBook();

        assertEquals(5, people.size());
        assertPersonDetails(people.get(0), "Bill McKnight", "Male", parseDate("16/03/77"));
        assertPersonDetails(people.get(1), "Paul Robinson", "Male", parseDate("15/01/85"));
        assertPersonDetails(people.get(2), "Gemma Lane", "Female", parseDate("20/11/91"));
        assertPersonDetails(people.get(3), "Sarah Stone", "Female", parseDate("20/09/80"));
        assertPersonDetails(people.get(4), "Wes Jackson", "Male", parseDate("14/08/74"));
    }

    private Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("dd/MM/yy").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void assertPersonDetails(Person person, String name, String gender, Date birthDate) {
        assertEquals(name, person.getName());
        assertEquals(gender, person.getGender());
        assertEquals(birthDate, person.getBirthDate());
    }

    @Test
    public void testCountMaleSuccess() {
        //Local variable in case there is a need to test each function with a different file
        String testFilePath = "src/test/test_resources/AddressBook.txt";
        addressBook.readAddressBookFromFile(testFilePath);

        assertEquals(3, addressBook.countMales());
    }

    @Test
    public void testFindOldPersonSuccess() {
        //Local variable in case there is a need to test each function with a different file
        String testFilePath = "src/test/test_resources/AddressBook.txt";
        addressBook.readAddressBookFromFile(testFilePath);

        assertEquals("Wes Jackson", addressBook.findOldestPerson().getName());
    }

}
