package com.example.lockly.application.exceptions.user;

public class UserAlreadyRegisteredException extends RuntimeException{
    public UserAlreadyRegisteredException() {
        super("User already registered in system.");
    }
}
