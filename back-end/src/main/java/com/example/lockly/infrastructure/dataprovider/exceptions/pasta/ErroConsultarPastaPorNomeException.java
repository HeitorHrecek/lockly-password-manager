package com.example.lockly.infrastructure.dataprovider.exceptions.pasta;

public class ErroConsultarPastaPorNomeException extends RuntimeException {
    public ErroConsultarPastaPorNomeException(String mensagem) {
        super(mensagem);
    }
}
