package com.example.lockly.infrastructure.dataprovider.exceptions.senha;

public class ErroListarSenhasPorUsuarioException extends RuntimeException {
    public ErroListarSenhasPorUsuarioException(String mensagem) {
        super(mensagem);
    }
}
