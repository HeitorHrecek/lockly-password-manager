package com.example.lockly.infrastructure.dataprovider.exceptions.pasta;

public class ErroAoListarPastasPorUsuarioException extends RuntimeException {
    public ErroAoListarPastasPorUsuarioException(String mensagem) {
        super(mensagem);
    }
}
