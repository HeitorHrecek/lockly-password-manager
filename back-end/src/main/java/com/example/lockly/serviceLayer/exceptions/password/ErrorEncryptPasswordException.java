package com.example.lockly.serviceLayer.exceptions.password;

public class ErrorEncryptPasswordException extends RuntimeException {
    public ErrorEncryptPasswordException(String message) {
        super("Error encrypting password: " + message);
    }
}
