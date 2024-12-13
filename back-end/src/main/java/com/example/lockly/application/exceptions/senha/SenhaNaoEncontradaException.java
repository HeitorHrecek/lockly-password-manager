package com.example.lockly.application.exceptions.senha;

public class SenhaNaoEncontradaException extends RuntimeException {
    public SenhaNaoEncontradaException() {
        super("Senha não encontrada.");
    }
}
