package com.example.lockly.serviceLayer.exceptions.folder;

public class NoFolderFoundException extends RuntimeException {
    public NoFolderFoundException(String message) {
        super(message);
    }
}
