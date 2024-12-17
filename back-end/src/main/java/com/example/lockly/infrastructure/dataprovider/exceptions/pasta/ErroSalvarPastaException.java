package com.example.lockly.infrastructure.dataprovider.exceptions.pasta;

public class ErroSalvarPastaException extends RuntimeException {
    public ErroSalvarPastaException(String mensagem) {
        super(mensagem);
    }
}
