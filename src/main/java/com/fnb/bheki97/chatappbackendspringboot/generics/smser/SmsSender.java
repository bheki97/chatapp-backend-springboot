package com.fnb.bheki97.chatappbackendspringboot.generics.smser;

import com.fnb.bheki97.chatappbackendspringboot.model.SmsRequest;

public interface SmsSender {

    void sendSms(SmsRequest request);
}
