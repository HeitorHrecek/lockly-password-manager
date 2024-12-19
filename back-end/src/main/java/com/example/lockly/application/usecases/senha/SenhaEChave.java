package com.example.lockly.application.usecases.senha;

import lombok.Builder;

import javax.crypto.SecretKey;

@Builder
public record SenhaEChave(SecretKey chave, String senhaCriptografada) {}
