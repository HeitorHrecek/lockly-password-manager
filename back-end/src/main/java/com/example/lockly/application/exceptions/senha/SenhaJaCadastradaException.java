package com.example.lockly.application.exceptions.senha;

public class SenhaJaCadastradaException extends RuntimeException {
    public SenhaJaCadastradaException() {
        super("Senha ja está cadastrada.");
    }
}
