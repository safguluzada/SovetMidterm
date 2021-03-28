package com.ada.dto;

public class ResponseDTO {

    public String message;

    public static ResponseDTO responseDTO = new ResponseDTO();



    private ResponseDTO() {
    }

    public  ResponseDTO success(String message){
        return setMessage(message);
    }

    public  ResponseDTO error(String message){
        return setMessage(message);
    }

    public static ResponseDTO getInstance(){

        return responseDTO;
    }

    public ResponseDTO(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public ResponseDTO setMessage(String message) {
        this.message = message;
        return this;
    }
}
