package com.fnb.bheki97.chatappbackendspringboot.service.registration.impl;

import com.fnb.bheki97.chatappbackendspringboot.entity.Geek;
import com.fnb.bheki97.chatappbackendspringboot.exception.ChatAppException;
import com.fnb.bheki97.chatappbackendspringboot.repository.GeekRepository;
import com.fnb.bheki97.chatappbackendspringboot.service.registration.GeekRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.TemporalField;

@Service
public class GeekRegistrationService implements GeekRegistration {

    @Autowired
    private GeekRepository geekRepository;
    @Autowired
    private PasswordEncoder encoder;


    @Override
    public boolean persistGeekInformation(Geek geek) {

        if(validateGeekData(geek)){
            //set username to email
            geek.setUsername(geek.getEmail());

            //encode password
            geek.setPassword(encoder.encode(geek.getPassword()));

            //set registration to now
            geek.setRegistrationDate(new Timestamp(System.currentTimeMillis()));

            System.out.println(geekRepository.save(geek));


            return true;

        }

        return false;
    }

    private boolean validateGeekData(Geek geek) {

        //check if the members are null
        if(checkForNullAndEmpty(geek.getFirstname())|| checkForNullAndEmpty(geek.getLastname()) ||
                checkForNullAndEmpty(geek.getEmail())||checkForNullAndEmpty(geek.getCellNumber()) ||
                checkForNullAndEmpty(geek.getPassword())){
            throw new ChatAppException("Cannot any of the geek's information as null or empty fields");
        }

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

    private boolean checkForNullAndEmpty(String string){
        return string==null||string.isEmpty();
    }


}
