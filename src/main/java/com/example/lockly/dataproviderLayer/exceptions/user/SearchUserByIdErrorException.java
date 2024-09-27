package com.example.lockly.dataproviderLayer.exceptions.user;

public class SearchUserByIdErrorException extends RuntimeException {
    public SearchUserByIdErrorException(String message) {
        super(message);
    }
}
