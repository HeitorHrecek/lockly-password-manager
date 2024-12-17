package com.example.lockly.domain;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Usuario {
    private Integer id;
    private String nome;
    private String email;
    private String senha;

    public void setData(Usuario alteredUsuario){
        if (alteredUsuario.getNome() != null){
            this.nome = alteredUsuario.nome;
        }
        if (alteredUsuario.getEmail() != null){
            this.email = alteredUsuario.email;
        }
    }
}
