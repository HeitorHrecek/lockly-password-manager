package com.example.lockly.serviceLayer.exceptions.folder;

public class FolderAlreadyRegisteredException extends RuntimeException {
    public FolderAlreadyRegisteredException(String message) {
        super(message);
    }
}
