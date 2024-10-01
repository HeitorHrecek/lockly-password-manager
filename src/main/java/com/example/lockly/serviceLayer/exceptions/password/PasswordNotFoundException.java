package com.example.lockly.serviceLayer.exceptions.password;

public class PasswordNotFoundException extends RuntimeException {
    public PasswordNotFoundException() {
        super("Password not found");
    }
}
