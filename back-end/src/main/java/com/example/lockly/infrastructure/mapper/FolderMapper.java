package com.example.lockly.infrastructure.mapper;

import com.example.lockly.entrypoint.dto.PastaDto;
import com.example.lockly.infrastructure.repositoryLayer.entities.FolderEntity;

public abstract class FolderMapper {

    public static com.example.lockly.domain.Pasta forDomain(FolderEntity entity) {
        return com.example.lockly.domain.Pasta.builder()
                .id(entity.getId())
                .nome(entity.getName())
                .usuario(UserMapper.forDomain(entity.getUserEntity()))
                .build();
    }

    public static FolderEntity forEntity(com.example.lockly.domain.Pasta domain) {
        return FolderEntity.builder()
                .id(domain.getId())
                .name(domain.getNome())
                .userEntity(UserMapper.forEntity(domain.getUsuario()))
                .build();
    }




}
