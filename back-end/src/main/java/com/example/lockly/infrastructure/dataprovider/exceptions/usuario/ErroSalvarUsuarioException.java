package com.example.lockly.infrastructure.dataprovider.exceptions.usuario;

public class ErroSalvarUsuarioException extends RuntimeException {
    public ErroSalvarUsuarioException(String mensagem) {
        super(mensagem);
    }
}