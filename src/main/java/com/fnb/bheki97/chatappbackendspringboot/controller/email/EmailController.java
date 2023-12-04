package com.fnb.bheki97.chatappbackendspringboot.controller.email;

import com.fnb.bheki97.chatappbackendspringboot.generics.emailer.EmailSender;
import com.fnb.bheki97.chatappbackendspringboot.model.EmailMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailSender sender;

    @PostMapping("/email")
    public void SendEmail(@RequestBody EmailMessage message){
        sender.sendEmail(message);
    }

}
