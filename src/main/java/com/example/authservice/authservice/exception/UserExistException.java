package com.example.authservice.authservice.exception;

public class UserExistException extends RuntimeException {
    public UserExistException(String username) {
        super("The username: " + username + "already exists");
    }
}
