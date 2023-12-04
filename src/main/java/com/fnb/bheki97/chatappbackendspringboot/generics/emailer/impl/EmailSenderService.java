package com.fnb.bheki97.chatappbackendspringboot.generics.emailer.impl;

import com.fnb.bheki97.chatappbackendspringboot.generics.emailer.EmailSender;
import com.fnb.bheki97.chatappbackendspringboot.model.EmailMessage;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("emailServiceSender")
public class EmailSenderService implements EmailSender {


    private final JavaMailSender javaMailSender;

    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    @Override
    public void sendEmail(EmailMessage message) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {

            helper= new MimeMessageHelper(mimeMessage, true);
            helper.setTo(message.getReceivers());
            helper.setSubject(message.getSubject());
            helper.setText(message.getMessage(), message.isHtml());

            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            System.out.println(e.getMessage());
        }

    }
}
