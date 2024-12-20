package com.example.lockly.infrastructure.mapper;

import com.example.lockly.infrastructure.repository.entities.PastaEntity;

public abstract class PastaMapper {

    public static com.example.lockly.domain.Pasta paraDomain(PastaEntity entity) {
        return com.example.lockly.domain.Pasta.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .usuario(UserMapper.paraDomain(entity.getUsuarioEntity()))
                .build();
    }

    public static PastaEntity paraEntity(com.example.lockly.domain.Pasta domain) {
        return PastaEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .usuarioEntity(UserMapper.paraEntity(domain.getUsuario()))
                .build();
    }




}
