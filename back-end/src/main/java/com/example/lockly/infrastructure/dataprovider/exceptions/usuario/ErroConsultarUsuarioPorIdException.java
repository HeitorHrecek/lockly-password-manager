package com.example.lockly.infrastructure.dataprovider.exceptions.usuario;

public class ErroConsultarUsuarioPorIdException extends RuntimeException {
    public ErroConsultarUsuarioPorIdException(String mensagem) {
        super(mensagem);
    }
}
