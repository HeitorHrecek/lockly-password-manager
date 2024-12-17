package com.example.lockly.infrastructure.dataprovider.exceptions.senha;

public class ErroAoListarSenhasPorPastaException extends RuntimeException {
    public ErroAoListarSenhasPorPastaException(String mensagem) {
        super(mensagem);
    }
}
