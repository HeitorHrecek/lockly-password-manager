package com.example.lockly.infrastructure.repository.entities.senha;


import com.example.lockly.infrastructure.repository.entities.PastaEntity;
import com.example.lockly.infrastructure.repository.entities.UsuarioEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.crypto.SecretKey;

@Entity(name = "SenhaComPasta")
@Table(name = "senhas_pasta")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SenhaComPastaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    private String conteudo;
    private SecretKey chaveCriptografia;
    @ManyToOne
    private UsuarioEntity usuarioEntity;
    @ManyToOne
    private PastaEntity pastaEntity;

}
