package com.ada.utils;


import lombok.extern.java.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Log
public class EncryptPassword {


    public static String encryptPassword (String password) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");

            messageDigest.update(password.getBytes());

            byte[] resultByteArray = messageDigest.digest();

            StringBuilder stringBuilder = new StringBuilder();

            for (byte bytes : resultByteArray) {
                stringBuilder.append(String.format("%02x", bytes));
            }

            return stringBuilder.toString();

        } catch (NoSuchAlgorithmException exception) {
            exception.printStackTrace();
            log.warning(exception.getMessage());
        }

        return new String("");
    }

}
