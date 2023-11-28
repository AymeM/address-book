package com.deloitte;

import java.util.Date;

public class Person {

    private String name;
    private String gender;
    private Date birthDate;

    //TODO find a good approach to convert name to first and last names. Split by " " is ok but will not cover cases like Charles De Gaulle
    public Person(String name, String gender, Date birthDate) {
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
