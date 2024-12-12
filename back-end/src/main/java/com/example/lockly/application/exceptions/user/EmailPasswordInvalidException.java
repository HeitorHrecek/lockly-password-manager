package com.example.lockly.application.exceptions.user;

public class EmailPasswordInvalidException extends RuntimeException {
    public EmailPasswordInvalidException() {
        super("Password or Email is incorrect.");
    }
}
