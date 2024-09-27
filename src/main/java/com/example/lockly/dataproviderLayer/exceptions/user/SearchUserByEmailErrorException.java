package com.example.lockly.dataproviderLayer.exceptions.user;

public class SearchUserByEmailErrorException extends RuntimeException{
    public SearchUserByEmailErrorException(String message) {
        super(message);
    }
}
