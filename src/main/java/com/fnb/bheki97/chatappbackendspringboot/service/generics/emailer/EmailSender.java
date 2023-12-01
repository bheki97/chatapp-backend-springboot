package com.fnb.bheki97.chatappbackendspringboot.service.generics.emailer;


import com.fnb.bheki97.chatappbackendspringboot.service.generics.model.EmailMessage;

public interface EmailSender {

    void sendEmail(EmailMessage message);
}
