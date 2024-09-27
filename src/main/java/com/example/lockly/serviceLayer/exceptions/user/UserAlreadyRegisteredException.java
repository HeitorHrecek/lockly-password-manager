package com.example.lockly.serviceLayer.exceptions.user;

public class UserAlreadyRegisteredException extends RuntimeException{
    public UserAlreadyRegisteredException() {
        super("User already registered in system.");
    }
}
