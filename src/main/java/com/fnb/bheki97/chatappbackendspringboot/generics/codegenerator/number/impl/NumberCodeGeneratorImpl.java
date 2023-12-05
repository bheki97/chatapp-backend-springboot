package com.fnb.bheki97.chatappbackendspringboot.generics.codegenerator.number.impl;

import com.fnb.bheki97.chatappbackendspringboot.generics.codegenerator.number.NumberCodeGenerator;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class NumberCodeGeneratorImpl implements NumberCodeGenerator {

    private final Random random;

    public NumberCodeGeneratorImpl() {
        random = new Random();
    }

    @Override
    public Long generateCode(int limit) {

        return random.nextLong(getHighestVal(limit))+getLowestVal(limit);
    }

    private long getHighestVal(int limit){
        long val= 1;
        for(int i=0;i<limit;i++){
            val = val*10;
        }
        return val-1;
    }
    private long getLowestVal(int limit){
        long val= 1;
        for(int i=1;i<limit;i++){
            val = val*10;
        }
        return val;
    }


    public static void main(String[] args) {
        NumberCodeGeneratorImpl impl = new NumberCodeGeneratorImpl();

        int limit =2;

    }
}
