package com.example.resttest.helloworld;

import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Locale;

@RestController
public class HelloWorldController {

    MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("hello-world")
    public String getHelloWorld(){
        return new String("Hello World");
    }

    @GetMapping("hello-world-internationalized")
    public String getHelloWorldInternationalized(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message", null, "default message", locale);
    }

    @GetMapping("hello-world-bean")
    public HelloWorldBean getHelloWorldBean(){
        return new HelloWorldBean("Hello World");
    }

    @GetMapping("hello-world-bean/path-variable/{name}")
    public HelloWorldBean getHelloWorldPathVariable(@PathVariable String name){
        return new HelloWorldBean("Hello World"+": "+name);
    }
}
