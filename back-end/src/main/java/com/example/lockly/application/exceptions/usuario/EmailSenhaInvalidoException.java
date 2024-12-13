package com.example.lockly.application.exceptions.usuario;

public class EmailSenhaInvalidoException extends RuntimeException {
    public EmailSenhaInvalidoException() {
        super("Senha ou email invalidos.");
    }
}
