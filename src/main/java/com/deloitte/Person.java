package com.deloitte;

import java.util.Date;

public class Person {

    private String name;
    private String gender;
    private Date birthDate;

    //TODO find a good approach to convert name to first and last names. Split by " " is ok but will not cover cases like Charles De Gaulle
    public Person(String name, String gender, Date birthDate) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (!"Male".equalsIgnoreCase(gender) && !"Female".equalsIgnoreCase(gender)) {
            throw new IllegalArgumentException("Gender must be either 'Male' or 'Female'");
        }
        if (birthDate == null) {
            throw new IllegalArgumentException("Birth date cannot be null");
        }
        this.name = name;
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }
}
