package com.example.lockly.application.exceptions.password;

public class NoPasswordFoundException extends RuntimeException {
    public NoPasswordFoundException() {
        super("No password found");
    }
}
