package com.example.lockly.dataproviderLayer.exceptions.user;

public class UserSearchByEmailErrorException extends RuntimeException{
    public UserSearchByEmailErrorException(String message) {
        super(message);
    }
}
