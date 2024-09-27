package com.example.lockly.dataproviderLayer.exceptions.user;

public class SaveUserErrorException extends RuntimeException {
    public SaveUserErrorException(String message) {
        super(message);
    }
}