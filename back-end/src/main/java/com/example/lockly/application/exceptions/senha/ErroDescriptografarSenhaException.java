package com.example.lockly.application.exceptions.senha;

public class ErroDescriptografarSenhaException extends RuntimeException {
    public ErroDescriptografarSenhaException(String message) {
        super("Erro ao descriptografar senha. " + message);
    }
}
