package com.example.lockly.mapper;


import com.example.lockly.entrypoint.dto.UsuarioDto;
import com.example.lockly.domainLayer.User;
import com.example.lockly.repositoryLayer.entities.UserEntity;

public abstract class UserMapper {
    public static User forDomain(UserEntity entity) {
        return User.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .password(entity.getPassword())
                .build();
    }

    public static UserEntity forEntity(User domain) {
        return UserEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .build();
    }

    public static UsuarioDto forDto(User domain) {
        return UsuarioDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .build();
    }

    public static User forDomainFromDto(UsuarioDto dto) {
        return User.builder()
                .id(dto.id())
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();
    }
}
