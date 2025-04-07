package com.example.resttest.versioning;

public class Personv2 {
    Name name;
    public Personv2(Name name) {
    this.name = name;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }
}
