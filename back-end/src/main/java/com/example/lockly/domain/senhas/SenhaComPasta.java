package com.example.lockly.domain.senhas;

import com.example.lockly.domain.Pasta;
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
public class SenhaComPasta {
    private Integer id;
    private String nome;
    private String conteudo;
    private Usuario usuario;
    private Pasta pasta;
    private SecretKey senhaCriptografia;
}
