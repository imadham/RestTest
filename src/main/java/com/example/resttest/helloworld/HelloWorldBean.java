package com.example.resttest.helloworld;

public class HelloWorldBean {

    String text;
    public HelloWorldBean(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
