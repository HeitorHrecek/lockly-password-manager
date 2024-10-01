package com.example.lockly.serviceLayer.exceptions.password;

public class NoPasswordFoundException extends RuntimeException {
    public NoPasswordFoundException() {
        super("No password found");
    }
}
