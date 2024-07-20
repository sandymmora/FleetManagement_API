package com.fleetmanagement.api_rest.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(String error){
        super(error);
    }
}
