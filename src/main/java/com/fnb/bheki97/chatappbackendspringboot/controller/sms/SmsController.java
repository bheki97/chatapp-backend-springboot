package com.fnb.bheki97.chatappbackendspringboot.controller.sms;

import com.fnb.bheki97.chatappbackendspringboot.generics.smser.SmsSender;
import com.fnb.bheki97.chatappbackendspringboot.model.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sms") public class SmsController {

    @Autowired
    private SmsSender sender;

    @PostMapping
    public String sendSms(@RequestBody SmsRequest request){
        sender.sendSms(request);

        return "message sent";
    }
}
