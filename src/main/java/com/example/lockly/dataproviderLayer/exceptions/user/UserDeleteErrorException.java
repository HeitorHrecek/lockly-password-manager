package com.example.lockly.dataproviderLayer.exceptions.user;

public class UserDeleteErrorException extends RuntimeException {
    public UserDeleteErrorException(String message) {
        super(message);
    }
}
