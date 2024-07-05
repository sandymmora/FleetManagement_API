package com.fleetmanagement.api_rest.exception;

public class EmailAlreadyExist extends RuntimeException{
    public EmailAlreadyExist(String error){
        super(error);
    }
}
