package com.example.lockly.application.exceptions.pasta;

public class NenhumaPastaEncontradaException extends RuntimeException {
    public NenhumaPastaEncontradaException() {
        super("Nenhuma pasta encontrada.");
    }
}
