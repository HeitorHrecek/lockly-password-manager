package com.example.lockly.application.exceptions.senha;

public class ErroCriptografarSenhaException extends RuntimeException {
    public ErroCriptografarSenhaException(String message) {
        super("Erro ao criptografar senha. " + message);
    }
}
