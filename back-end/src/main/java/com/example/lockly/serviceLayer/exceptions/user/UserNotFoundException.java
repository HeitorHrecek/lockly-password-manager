package com.example.lockly.serviceLayer.exceptions.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException() {
        super("User not found.");
    }
}
