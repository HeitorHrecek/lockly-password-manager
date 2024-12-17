package com.example.lockly.domain.passwords;

import com.example.lockly.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKey;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class SenhaSemPasta {
    private Integer id;
    private String nome;
    private String conteudo;
    private Usuario usuario;
    private SecretKey chaveCriptografia;
}