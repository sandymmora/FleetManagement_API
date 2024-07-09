package com.fleetmanagement.api_rest.exception;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(value = {BadRequestException.class})
    public ResponseEntity<Object> handleBadRequestException(BadRequestException badRequestException) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiException apiException = new ApiException(badRequestException.getMessage());
        return new ResponseEntity<>(apiException, badRequest);
    }
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFoundException(NotFoundException notFoundException){
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiException apiException = new ApiException(notFoundException.getMessage());
        return new ResponseEntity<>(apiException, notFound);
    }
    @ExceptionHandler(value = {MissingRequiredFields.class})
    public ResponseEntity<Object> handleMissingRequiredFields(MissingRequiredFields missingRequiredFielsException){
        HttpStatus missingRequiredFields = HttpStatus.INTERNAL_SERVER_ERROR;
        ApiException apiException = new ApiException(missingRequiredFielsException.getMessage());
        return new ResponseEntity<>(apiException, missingRequiredFields);
    }
    @ExceptionHandler(value = {EmailAlreadyExist.class})
    public ResponseEntity<Object> handleEmailAlreadyExist(EmailAlreadyExist emailAlreadyExist){
        HttpStatus status = HttpStatus.CONFLICT;
        ApiException apiException = new ApiException(emailAlreadyExist.getMessage());
        return new ResponseEntity<>(apiException,status);
    }
}
