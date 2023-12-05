package com.fnb.bheki97.chatappbackendspringboot.service.geekverifier;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender.emailverifier.GeekEmailVerifier;
import com.fnb.bheki97.chatappbackendspringboot.service.geekverifier.verificationsender.smsverifier.GeekSmsVerifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeekVerifierService implements GeekVerifier{

    @Autowired
    private GeekRepository geekRepository;

    @Autowired
    private GeekEmailVerifier emailVerifier;
    @Autowired
    private GeekSmsVerifier smsVerifier;



    @Override
    public boolean verifyGeek(Geek geek) {

        if(validateGeekData(geek)){
            emailVerifier.sendGeekVerificationCode(geek);
            smsVerifier.sendGeekVerificationCode(geek);
        }

        return true;
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
