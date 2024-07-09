package com.fleetmanagement.api_rest.exception;

public class MissingRequiredFields extends RuntimeException {
    public MissingRequiredFields(String error){
        super(error);
    }
}
