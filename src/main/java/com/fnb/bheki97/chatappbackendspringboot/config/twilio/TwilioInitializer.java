package com.fnb.bheki97.chatappbackendspringboot.config.twilio;

import com.fnb.bheki97.chatappbackendspringboot.config.sms.SmsConfigProperties;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioInitializer {


    private final SmsConfigProperties properties;


    @Autowired
    public TwilioInitializer(SmsConfigProperties properties) {
        this.properties = properties;
        Twilio.init(properties.getSid(),properties.getToken());
    }
}
