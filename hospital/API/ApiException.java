package com.example.hospital.API;

public class ApiException extends RuntimeException {

    public ApiException(String message){
        super(message);
    }
}
