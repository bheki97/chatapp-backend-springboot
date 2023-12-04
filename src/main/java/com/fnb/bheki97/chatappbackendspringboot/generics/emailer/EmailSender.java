package com.fnb.bheki97.chatappbackendspringboot.generics.emailer;


import com.fnb.bheki97.chatappbackendspringboot.model.EmailMessage;

public interface EmailSender {

    void sendEmail(EmailMessage message);
}
