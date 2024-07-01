package com.fleetmanagement.api_rest.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String error) {
        super(error);
    }
}
