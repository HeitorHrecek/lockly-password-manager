package com.example.lockly.infrastructure.dataprovider.exceptions.senha;

public class ErroConsultarSenhaPorIdException extends RuntimeException {
    public ErroConsultarSenhaPorIdException(String mensagem) {
        super(mensagem);
    }
}
