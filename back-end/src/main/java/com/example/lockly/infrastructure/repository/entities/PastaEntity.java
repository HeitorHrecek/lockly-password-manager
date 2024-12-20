package com.example.lockly.infrastructure.repository.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity(name = "Pasta")
@Table(name = "pastas")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PastaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;
    @ManyToOne
    private UsuarioEntity usuarioEntity;
}
