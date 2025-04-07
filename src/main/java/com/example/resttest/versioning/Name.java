package com.example.resttest.versioning;

public class Name {
    String firstName;
    String lastName;
    public Name(String fname, String lname) {
        firstName = fname;
        lastName = lname;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
