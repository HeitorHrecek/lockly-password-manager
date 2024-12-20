package com.example.lockly.infrastructure.mapper;


import com.example.lockly.domain.Usuario;
import com.example.lockly.infrastructure.repository.entities.UsuarioEntity;

public abstract class UserMapper {
    public static Usuario paraDomain(UsuarioEntity entity) {
        return Usuario.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .email(entity.getEmail())
                .senha(entity.getSenha())
                .build();
    }

    public static UsuarioEntity paraEntity(Usuario domain) {
        return UsuarioEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .email(domain.getEmail())
                .senha(domain.getSenha())
                .build();
    }
}
