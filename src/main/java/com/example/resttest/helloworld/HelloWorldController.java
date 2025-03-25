package com.example.resttest.helloworld;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @GetMapping("hello-world")
    public String getHelloWorld(){
        return new String("Hello World");
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
