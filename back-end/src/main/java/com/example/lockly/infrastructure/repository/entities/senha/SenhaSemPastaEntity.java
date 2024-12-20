package com.example.lockly.infrastructure.repository.entities.senha;

import com.example.lockly.infrastructure.repository.entities.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.crypto.SecretKey;

@Entity(name = "SenhaSemPasta")
@Table(name = "senhas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SenhaSemPastaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String conteudo;
    private SecretKey chaveCriptografia;
    @ManyToOne
    private UsuarioEntity usuarioEntity;

}
