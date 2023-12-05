package com.fnb.bheki97.chatappbackendspringboot.generics.codegenerator.string.impl;

import com.fnb.bheki97.chatappbackendspringboot.generics.codegenerator.string.StringCodeGenerator;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class StringCodeGeneratorImpl implements StringCodeGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private SecureRandom random;
    private StringBuilder randomString;
    public StringCodeGeneratorImpl() {
         random = new SecureRandom();
    }

    @Override
    public String generateCode(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        randomString = new StringBuilder(limit);
        random = new SecureRandom();

        for (int i = 0; i < limit; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }


    @Override
    public String generateCode(int limit, String chars) {
        if (limit <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        randomString = new StringBuilder(limit);


        for (int i = 0; i < limit; i++) {
            int randomIndex = random.nextInt(chars.length());
            char randomChar = chars.charAt(randomIndex);
            randomString.append(randomChar);
        }

        return randomString.toString();
    }
}
