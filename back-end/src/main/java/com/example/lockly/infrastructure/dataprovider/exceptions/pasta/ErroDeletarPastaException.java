package com.example.lockly.infrastructure.dataprovider.exceptions.pasta;

public class ErroDeletarPastaException extends RuntimeException {
    public ErroDeletarPastaException(String mensagem) {
        super(mensagem);
    }
}
