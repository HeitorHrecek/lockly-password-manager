package com.example.lockly.application.exceptions.pasta;

public class PastaJaCadastradaException extends RuntimeException {
    public PastaJaCadastradaException() {
        super("Pasta já cadastrada com este nome no sistema.");
    }
}
