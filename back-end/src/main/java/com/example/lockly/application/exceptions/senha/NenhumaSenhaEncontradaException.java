package com.example.lockly.application.exceptions.senha;

public class NenhumaSenhaEncontradaException extends RuntimeException {
    public NenhumaSenhaEncontradaException() {
        super("Nenhuma senha encontrada.");
    }
}
