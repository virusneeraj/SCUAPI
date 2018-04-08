package com.scu.controller;

import com.scu.model.CustomResponse;
import com.scu.model.Message;
import com.scu.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/anonymous")
public class AnonymousController {
    @Autowired
    EmailService emailService;

    @Value("${scuapi.anonymous.message.to}")
    String anonymous_message_send_to;

    @PostMapping("/message")
    public CustomResponse sendMessage(@RequestBody Message message){
        boolean email_send_flag = emailService.sendEmail(anonymous_message_send_to, message.getSubject(),message.toString());
        CustomResponse customResponse = new CustomResponse();
        if(email_send_flag) {
            customResponse.setCode("CR200");
            customResponse.setMessage("SUCCESS");
        }else {
            customResponse.setCode("CR000");
            customResponse.setMessage("FAIL");
        }
        customResponse.setData(email_send_flag);
        return customResponse;
    }
}
