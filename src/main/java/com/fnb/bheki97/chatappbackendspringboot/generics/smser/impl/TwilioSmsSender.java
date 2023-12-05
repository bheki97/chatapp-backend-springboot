package com.fnb.bheki97.chatappbackendspringboot.generics.smser.impl;

import com.fnb.bheki97.chatappbackendspringboot.config.sms.SmsConfigProperties;
import com.fnb.bheki97.chatappbackendspringboot.generics.smser.SmsSender;
import com.fnb.bheki97.chatappbackendspringboot.model.SmsRequest;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TwilioSmsSender implements SmsSender {

    @Autowired
    private SmsConfigProperties properties;

    @Override
    public void sendSms(SmsRequest request) {
        MessageCreator creator = Message.creator(
                new PhoneNumber(request.getPhoneNumber()),
                new PhoneNumber(properties.getPhoneNumber()),
                request.getMessage()
        );

        creator.create();
    }
}
