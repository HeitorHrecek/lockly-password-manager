package com.example.lockly.serviceLayer.exceptions.user;

public class UserAlreadyRegisterException extends RuntimeException{
    public UserAlreadyRegisterException() {
        super("User already registered in system.");
    }
}
