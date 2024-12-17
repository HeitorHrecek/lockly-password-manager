package com.example.lockly.infrastructure.dataprovider.exceptions.senha;

public class ErroSalvarSenhaException extends RuntimeException {
    public ErroSalvarSenhaException(String mensagem) {
        super(mensagem);
    }
}
