package com.deloitte;

import com.deloitte.util.DateUtil;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

public class AddressBook {

    private static final Logger LOGGER = Logger.getLogger(AddressBook.class.getName());
    private List<Person> addressBook = new ArrayList<>();

    public void readAddressBookFromFile(String filePath) {
        LOGGER.info("Starting to read address book from file: " + filePath);
        FileReader fileReader = new FileReader();
        try {
            fileReader.readFromFile(filePath, this::processAddressBookLine);
        } catch (RuntimeException e) {
            LOGGER.warning("Error occurred while reading the address book from file: " + filePath);
            throw e;
        }
        LOGGER.info("Completed reading address book from file.");
    }

    private void processAddressBookLine(String line) {
        String[] parts = line.split(", ");
        if (parts.length != 3) {
            LOGGER.warning("Malformed line: " + line);
            return;
        }
        try {
            String name = parts[0];
            String gender = parts[1];
            Date birthDate = DateUtil.parseDate(parts[2]);
            addressBook.add(new Person(name, gender, birthDate));
        } catch (Exception e) {
            LOGGER.warning("Error processing line: " + line + "; Error: " + e.getMessage());
        }
    }

    public int countMales() {
        return (int) addressBook.stream()
                .filter(person -> "Male".equals(person.getGender()))
                .count();
    }

    public Person findOldestPerson() {
        return addressBook.stream()
                .min(Comparator.comparing(Person::getBirthDate))
                .orElse(null);
    }

    //Prefer to use the full name instead of the firstname because it's a better identifier
    public int calculateAgeDifferenceInDays(String person1Name, String person2Name) {
        Person person1 = findPersonByName(person1Name);
        Person person2 = findPersonByName(person2Name);

        if (person1 == null || person2 == null) {
            if (person1 == null) {
                LOGGER.warning("Person not found: " + person1Name);
            }
            if (person2 == null) {
                LOGGER.warning("Person not found: " + person2Name);
            }
            return -1;
        }

        long differenceInMillis = person1.getBirthDate().getTime() - person2.getBirthDate().getTime();
        return Math.abs((int) (differenceInMillis / (24 * 60 * 60 * 1000))); // Convert milliseconds to days
    }

    private Person findPersonByName(String name) {
        for (Person person : addressBook) {
            if (name.equals(person.getName())) {
                return person;
            }
        }
        return null;
    }

    public List<Person> getAddressBook() {
        return addressBook;
    }
}
