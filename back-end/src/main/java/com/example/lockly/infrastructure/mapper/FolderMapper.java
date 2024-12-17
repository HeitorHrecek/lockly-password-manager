package com.example.lockly.infrastructure.mapper;

import com.example.lockly.entrypoint.dto.PastaDto;
import com.example.lockly.domain.Pasta;
import com.example.lockly.infrastructure.repositoryLayer.entities.FolderEntity;

public abstract class FolderMapper {

    public static Pasta forDomain(FolderEntity entity) {
        return Pasta.builder()
                .id(entity.getId())
                .nome(entity.getName())
                .usuario(UserMapper.forDomain(entity.getUserEntity()))
                .build();
    }

    public static FolderEntity forEntity(Pasta domain) {
        return FolderEntity.builder()
                .id(domain.getId())
                .name(domain.getNome())
                .userEntity(UserMapper.forEntity(domain.getUsuario()))
                .build();
    }

    public static PastaDto forDto(Pasta domain) {
        return PastaDto.builder()
                .id(domain.getId())
                .name(domain.getNome())
                .userDto(UserMapper.forDto(domain.getUsuario()))
                .build();
    }

    public static Pasta forDomainFromDto(PastaDto dto) {
        return Pasta.builder()
                .id(dto.id())
                .nome(dto.name())
                .usuario(UserMapper.forDomainFromDto(dto.userDto()))
                .build();
    }
}
