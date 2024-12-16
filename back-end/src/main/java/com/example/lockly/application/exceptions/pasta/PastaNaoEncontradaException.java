package com.example.lockly.application.exceptions.pasta;

public class PastaNaoEncontradaException extends RuntimeException {
    public PastaNaoEncontradaException() {
        super("Pasta não encontrada.");
    }
}
