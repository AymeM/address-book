package com.deloitte;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddressBook {

    private List<Person> addressBook = new ArrayList<>();

    public void readAddressBookFromFile(String filePath) {
        FileReader fileReader = new FileReader();
        try {
            fileReader.readFromFile(filePath, this::processAddressBookLine);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void processAddressBookLine(String line) {
        String[] parts = line.split(", ");
        String name = parts[0];
        String gender = parts[1];
        try {
            Date birthDate = new SimpleDateFormat("dd/MM/yy").parse(parts[2]);
            addressBook.add(new Person(name, gender, birthDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public void printAddressBook() {
        for (Person person : addressBook) {
            System.out.println("Name: " + person.getName() + ", Gender: " + person.getGender() + ", Birthdate: " + person.getBirthDate());
        }
    }

    public List<Person> getAddressBook() {
        return addressBook;
    }
}
