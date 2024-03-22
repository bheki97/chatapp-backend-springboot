package com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.emailsmsverifier.impl;

import com.fnb.bheki97.chatappbackendspringboot.dto.EmailSmsCodeDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.emailsmsverifier.EmailSmsGeekVerifier;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender.emailverifier.EmailVerificationCodeSenderService;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender.smsverifier.SmsVerificationCodeSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class EmailSmsGeekVerifierService implements EmailSmsGeekVerifier {


    private GeekRepository geekRepository;


    private EmailVerificationCodeSenderService emailVerifier;

    private SmsVerificationCodeSenderService smsVerifier;



    @Override
    public EmailSmsCodeDto verifyGeek(Geek geek) {

        EmailSmsCodeDto d = new EmailSmsCodeDto<>();


        if(validateGeekData(geek)){
            Object smsCode = smsVerifier.sendGeekVerificationCode(geek);
            d.setSmsCode(smsCode);
            Object emailCode = emailVerifier.sendGeekVerificationCode(geek);
            d.setEmailCode(emailCode);
        }

        return d;
    }


    private boolean validateGeekData(Geek geek) {

        //validate Email
        if(geekRepository.existsByEmail(geek.getEmail())){
            throw new ChatAppException("Geek with the same email is already registered");
        }

        //validate Cell number
//        if(geekRepository.existsByCellNumber(geek.getCellNumber())){
//            throw new ChatAppException("Geek with the same cell number is already registered");
//        }


        return true;
    }
}
