package com.example.lockly.dataproviderLayer.exceptions.user;

public class UserSaveErrorException extends RuntimeException {
    public UserSaveErrorException(String message) {
        super(message);
    }
}