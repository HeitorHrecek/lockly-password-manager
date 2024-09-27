package com.example.lockly.dataproviderLayer.exceptions.user;

public class DeleteUserErrorException extends RuntimeException {
    public DeleteUserErrorException(String message) {
        super(message);
    }
}
