package com.example.lockly.infrastructure.mapper;


import com.example.lockly.domain.Usuario;
import com.example.lockly.entrypoint.dto.UsuarioDto;
import com.example.lockly.infrastructure.repositoryLayer.entities.UserEntity;

public abstract class UserMapper {
    public static Usuario forDomain(UserEntity entity) {
        return Usuario.builder()
                .id(entity.getId())
                .nome(entity.getName())
                .email(entity.getEmail())
                .senha(entity.getPassword())
                .build();
    }

    public static UserEntity forEntity(Usuario domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .name(domain.getNome())
                .email(domain.getEmail())
                .password(domain.getSenha())
                .build();
    }

    public static UsuarioDto forDto(Usuario domain) {
        return UsuarioDto.builder()
                .id(domain.getId())
                .name(domain.getNome())
                .email(domain.getEmail())
                .password(domain.getSenha())
                .build();
    }

    public static Usuario forDomainFromDto(UsuarioDto dto) {
        return Usuario.builder()
                .id(dto.id())
                .nome(dto.name())
                .email(dto.email())
                .senha(dto.password())
                .build();
    }
}
