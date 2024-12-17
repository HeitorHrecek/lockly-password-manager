package com.example.lockly.infrastructure.dataprovider.exceptions.usuario;

public class ErroConsultarUsuarioPorEmailException extends RuntimeException{
    public ErroConsultarUsuarioPorEmailException(String mensagem) {
        super(mensagem);
    }
}
