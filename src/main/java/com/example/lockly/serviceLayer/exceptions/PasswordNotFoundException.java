package com.example.lockly.serviceLayer.exceptions;

public class PasswordNotFoundException extends RuntimeException {
    public PasswordNotFoundException() {
        super("Password not found");
    }
}
