package com.example.lockly.application.exceptions.password;

public class PasswordNotFoundException extends RuntimeException {
    public PasswordNotFoundException() {
        super("Password not found");
    }
}
