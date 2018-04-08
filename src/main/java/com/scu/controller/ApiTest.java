package com.scu.controller;

import com.scu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiTest {

    @Autowired
    EmailService emailService;

    @GetMapping("/")
    public String test(){
        return "Api is up & running";
    }

    @PostMapping("/sendEmail")
    public Boolean sendEmail(){
        return emailService.sendEmail("","test","Testing");
    }
}
