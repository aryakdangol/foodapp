package com.example.authservice.authservice.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AuthServiceException extends Exception{

    private HttpStatus status;

    private String errorCode;

    private String errorMessage;

    public AuthServiceException(HttpStatus status, String message, String errorCode){
        super(message);
        this.status = status;
        this.errorCode = errorCode;
        this.errorMessage = message;
    }


}
