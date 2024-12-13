package com.example.lockly.application.exceptions.usuario;

public class UsuarioJaCadastradoException extends RuntimeException{
    public UsuarioJaCadastradoException() {
        super("Usuario já cadastrado.");
    }
}
