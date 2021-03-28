package com.ada.utils;

import org.springframework.stereotype.Service;

import java.util.SplittableRandom;

@Service
public class TokenGeneration {


    public String generateToken(){
        SplittableRandom splittableRandom = new SplittableRandom();
        StringBuilder builder= new StringBuilder();
        for (int i=0; i<10; i++){
            builder.append(splittableRandom.nextInt(0,10));
        }
        return builder.toString();
    }
}
