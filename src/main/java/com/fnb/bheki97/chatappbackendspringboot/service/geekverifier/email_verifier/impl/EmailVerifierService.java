package com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.email_verifier.impl;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.email_verifier.EmailVerifier;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender.emailverifier.EmailVerificationCodeSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailVerifierService implements EmailVerifier {

    @Autowired
    private EmailVerificationCodeSenderService verificationCodeSenderService;

    @Override
    public Object verifyGeek(Geek geek) {
        Object obj = new Object();

        if(validateGeek(geek)){
            obj = verificationCodeSenderService.sendGeekVerificationCode(geek);
        }
        return obj;
    }

    private boolean validateGeek(Geek geek) {
        if(geek.getEmail()==null)
            throw new ChatAppException("Email required to send verification code");

        return true;
    }
}
