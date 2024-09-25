package com.example.lockly.mapper.passwords;

import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.domainLayer.User;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import com.example.lockly.mapper.UserMapper;
import com.example.lockly.repositoryLayer.entities.UserEntity;
import com.example.lockly.repositoryLayer.entities.passwords.PasswordWithoutFolderEntity;

import java.util.List;

public abstract class PasswordWithoutFolderMapper {

    public static PasswordWithoutFolder forDomain(PasswordWithoutFolderEntity entity) {
        return PasswordWithoutFolder.builder()
                .id(entity.getId())
                .name(entity.getName())
                .content(entity.getContent())
                .user(UserMapper.forDomain(entity.getUserEntity()))
                .build();

    }

    public static PasswordWithoutFolderEntity forEnttiy(PasswordWithoutFolder domain) {
        return PasswordWithoutFolderEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .content(domain.getContent())
                .userEntity(UserMapper.forEntity(domain.getUser()))
                .build();
    }

    public static PasswordWithoutFolderDto forDto(PasswordWithoutFolder domain) {
        return new PasswordWithoutFolderDto(domain.getId(),
                domain.getName(),
                domain.getContent(),
                UserMapper.forDto(domain.getUser()));
    }

    public static PasswordWithoutFolder forDomainFromDto(PasswordWithoutFolderDto dto) {
        return PasswordWithoutFolder.builder()
                .id(dto.id())
                .name(dto.name())
                .content(dto.content())
                .user(UserMapper.forDomainFromDto(dto.userDto()))
                .build();
    }

    public static List<PasswordWithoutFolderDto> forDtos(List<PasswordWithoutFolder> passwordWithFolderList) {
        return passwordWithFolderList.stream().map(PasswordWithoutFolderMapper::forDto).toList();
    }
}
