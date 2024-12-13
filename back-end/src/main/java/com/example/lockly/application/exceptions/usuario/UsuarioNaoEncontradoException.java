package com.example.lockly.application.exceptions.usuario;

public class UsuarioNaoEncontradoException extends RuntimeException{
    public UsuarioNaoEncontradoException() {
        super("Usuario não encontrado.");
    }
}
