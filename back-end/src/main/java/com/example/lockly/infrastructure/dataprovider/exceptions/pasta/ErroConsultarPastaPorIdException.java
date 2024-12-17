package com.example.lockly.infrastructure.dataprovider.exceptions.pasta;

public class ErroConsultarPastaPorIdException extends RuntimeException {
    public ErroConsultarPastaPorIdException(String mensagem) {
        super(mensagem);
    }
}