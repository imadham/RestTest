package com.example.resttest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public Personv1 getPersonV1(){
        return new Personv1("Imad Ali");
    }
    @GetMapping("/v2/person")
    public Personv2 getPersonV2(){
        return new Personv2(new Name("Imad", "Ali"));
    }


    @GetMapping(path = "person", params = "v1")
    public Personv1 getPersonV1Params(){
        return new Personv1("Imad Ali");

    }


    @GetMapping(path = "person", params = "v2")
    public Personv2 getPersonV2Params(){
        return new Personv2(new Name("Imad", "Ali"));
    }

}
