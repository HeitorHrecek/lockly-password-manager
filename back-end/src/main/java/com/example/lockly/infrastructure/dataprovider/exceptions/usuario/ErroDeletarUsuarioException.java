package com.example.lockly.infrastructure.dataprovider.exceptions.usuario;

public class ErroDeletarUsuarioException extends RuntimeException {
    public ErroDeletarUsuarioException(String mensagem) {
        super(mensagem);
    }
}
