package com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.emailsmsverifier.impl;

import com.fnb.bheki97.chatappbackendspringboot.dto.EmailSmsCodeDto;
import com.fnb.bheki97.chatappbackendspringboot.dto.EmailStringSmsLongDto;
import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.GeekVerifier;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.emailsmsverifier.EmailSmsGeekVerifier;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender.emailverifier.GeekEmailVerifier;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender.smsverifier.GeekSmsVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeekVerifierService implements EmailSmsGeekVerifier {

    @Autowired
    private GeekRepository geekRepository;

    @Autowired
    private GeekEmailVerifier emailVerifier;
    @Autowired
    private GeekSmsVerifier smsVerifier;



    @Override
    public EmailSmsCodeDto verifyGeek(Geek geek) {

        EmailSmsCodeDto d = new EmailSmsCodeDto<>();


        if(validateGeekData(geek)){
            Object smsCode = emailVerifier.sendGeekVerificationCode(geek);
            d.setSmsCode(smsCode);
            Object emailCode = smsVerifier.sendGeekVerificationCode(geek);
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
        if(geekRepository.existsByCellNumber(geek.getCellNumber())){
            throw new ChatAppException("Geek with the same cell number is already registered");
        }


        return true;
    }
}
