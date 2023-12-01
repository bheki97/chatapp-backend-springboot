package com.fnb.bheki97.chatappbackendspringboot.service.generics.model;

import lombok.Data;

import java.util.Arrays;
import java.util.Objects;

@Data
public class EmailMessage {

    private String[] receivers;
    private String subject;
    private String message;
    private boolean isHtml;

    public EmailMessage() {
    }

    public EmailMessage(String[] receivers, String subject, String message, boolean isHtml) {
        this.receivers = receivers;
        this.subject = subject;
        this.message = message;
        this.isHtml = isHtml;
    }

    @Override
    public String toString() {
        return "EmailMessage{" +
                "receivers=" + Arrays.toString(receivers) +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                ", isHtml=" + isHtml +
                '}';
    }
}
