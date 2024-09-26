package com.example.lockly.dataproviderLayer.exceptions;

public class DeleteUserErrorException extends RuntimeException {
    public DeleteUserErrorException(String message) {
        super(message);
    }
}
