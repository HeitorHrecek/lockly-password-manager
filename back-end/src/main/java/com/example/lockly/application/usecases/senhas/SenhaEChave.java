package com.example.lockly.application.usecases.senhas;

import lombok.Builder;

import javax.crypto.SecretKey;

@Builder
public record SenhaEChave(SecretKey chave, String senhaCriptografada) {
}
