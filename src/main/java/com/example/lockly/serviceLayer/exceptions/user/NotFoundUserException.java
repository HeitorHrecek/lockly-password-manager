package com.example.lockly.serviceLayer.exceptions.user;

public class NotFoundUserException extends RuntimeException{
    public NotFoundUserException() {
        super("User not found.");
    }
}
