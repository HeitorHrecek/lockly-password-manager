package com.example.lockly.serviceLayer.exceptions.password;

public class PasswordAlreadyRegisteredException extends RuntimeException {
    public PasswordAlreadyRegisteredException() {
        super("Password is already registered");
    }
}
