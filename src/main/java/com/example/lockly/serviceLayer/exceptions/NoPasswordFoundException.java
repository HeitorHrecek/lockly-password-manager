package com.example.lockly.serviceLayer.exceptions;

public class NoPasswordFoundException extends RuntimeException {
    public NoPasswordFoundException() {
        super("No password found");
    }
}
