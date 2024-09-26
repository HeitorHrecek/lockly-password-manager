package com.example.lockly.dataproviderLayer.exceptions;

public class SearchUserByIdErrorException extends RuntimeException {
    public SearchUserByIdErrorException(String message) {
        super(message);
    }
}
