package com.example.lockly.dataproviderLayer.exceptions.user;

public class UserSearchByIdErrorException extends RuntimeException {
    public UserSearchByIdErrorException(String message) {
        super(message);
    }
}
