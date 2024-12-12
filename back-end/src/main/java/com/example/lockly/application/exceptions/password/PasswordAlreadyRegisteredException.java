package com.example.lockly.application.exceptions.password;

public class PasswordAlreadyRegisteredException extends RuntimeException {
    public PasswordAlreadyRegisteredException() {
        super("Password is already registered");
    }
}
