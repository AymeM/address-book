package com.deloitte;

import java.util.Date;

public class Person {

    private String name;
    private String gender;
    private Date birthDate;

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
