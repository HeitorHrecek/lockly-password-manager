package com.example.lockly.mapper;


import com.example.lockly.entrypoint.dtos.UserDto;
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

    public static UserDto forDto(User domain) {
        return UserDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .email(domain.getEmail())
                .password(domain.getPassword())
                .build();
    }

    public static User forDomainFromDto(UserDto dto) {
        return User.builder()
                .id(dto.id())
                .name(dto.name())
                .email(dto.email())
                .password(dto.password())
                .build();
    }
}
