package com.example.lockly.dataproviderLayer.exceptions;

public class SaveUserErrorException extends RuntimeException {
    public SaveUserErrorException(String message) {
        super(message);
    }
}