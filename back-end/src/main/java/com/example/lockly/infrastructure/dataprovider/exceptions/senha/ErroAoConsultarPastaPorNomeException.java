package com.example.lockly.infrastructure.dataprovider.exceptions.senha;

public class ErroAoConsultarPastaPorNomeException extends RuntimeException {
    public ErroAoConsultarPastaPorNomeException(String mensagem) {
        super(mensagem);
    }
}
