package com.example.authservice.authservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthServiceExceptionHandler {

    @ExceptionHandler(AuthServiceException.class)
    public ResponseEntity<Object> handleAuthServiceException(AuthServiceException ex){
        ErrorResponse errorDto = new ErrorResponse(ex.getStatus(), ex.getErrorMessage(), ex.getErrorCode());
        return  new ResponseEntity<>(errorDto, ex.getStatus());
    }

}
