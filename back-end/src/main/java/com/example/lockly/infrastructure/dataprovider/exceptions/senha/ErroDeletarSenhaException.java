package com.example.lockly.infrastructure.dataprovider.exceptions.senha;

public class ErroDeletarSenhaException extends RuntimeException {
    public ErroDeletarSenhaException(String mensagem) {
        super(mensagem);
    }
}
