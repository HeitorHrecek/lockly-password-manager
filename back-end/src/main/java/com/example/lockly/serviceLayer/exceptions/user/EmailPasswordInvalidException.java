package com.example.lockly.serviceLayer.exceptions.user;

public class EmailPasswordInvalidException extends RuntimeException {
    public EmailPasswordInvalidException() {
        super("Password or Email is incorrect.");
    }
}
