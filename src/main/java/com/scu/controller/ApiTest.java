package com.scu.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiTest {

    @GetMapping("/")
    public String test(){
        return "Api is up & running";
    }
}
