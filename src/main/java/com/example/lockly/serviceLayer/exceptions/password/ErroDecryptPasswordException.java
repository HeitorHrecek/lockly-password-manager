package com.example.lockly.serviceLayer.exceptions.password;

public class ErroDecryptPasswordException extends RuntimeException {
    public ErroDecryptPasswordException(String message) {
        super("Error encrypting password: " + message);
    }
}
