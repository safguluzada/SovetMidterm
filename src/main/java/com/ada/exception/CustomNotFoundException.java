package com.ada.exception;


public class CustomNotFoundException extends RuntimeException{

    private String message;

    public CustomNotFoundException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
