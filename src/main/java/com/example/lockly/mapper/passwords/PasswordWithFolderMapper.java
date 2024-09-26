package com.example.lockly.mapper.passwords;

import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithFolderDto;
import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.mapper.FolderMapper;
import com.example.lockly.mapper.UserMapper;
import com.example.lockly.repositoryLayer.entities.passwords.PasswordWithFolderEntity;

import java.util.List;

public abstract class PasswordWithFolderMapper {

    public static PasswordWithFolder forDomain(PasswordWithFolderEntity entity) {
        return PasswordWithFolder.builder()
                .id(entity.getId())
                .name(entity.getName())
                .content(entity.getContent())
                .user(UserMapper.forDomain(entity.getUserEntity()))
                .build();
    }

    public static PasswordWithFolderEntity forEntity(PasswordWithFolder domain) {
        return PasswordWithFolderEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .content(domain.getContent())
                .userEntity(UserMapper.forEntity(domain.getUser()))
                .build();
    }

    public static PasswordWithFolderDto forDto(PasswordWithFolder domain) {
        return new PasswordWithFolderDto(domain.getId(),
                domain.getName(),
                domain.getContent(),
                UserMapper.forDto(domain.getUser()),
                FolderMapper.forDto(domain.getFolder()));
    }

    public static PasswordWithFolder forDomainFromDto(PasswordWithFolderDto dto) {
        return PasswordWithFolder.builder()
                .id(dto.id())
                .name(dto.name())
                .content(dto.content())
                .user(UserMapper.forDomainFromDto(dto.userDto()))
                .build();
    }

    public static List<PasswordWithFolderDto> forDtos(List<PasswordWithFolder> passwordWithFolderList) {
        return passwordWithFolderList.stream().map(PasswordWithFolderMapper::forDto).toList();
    }

    public static List<PasswordWithFolder> forDomains(List<PasswordWithFolderEntity> passwordWithFolderEntityList) {
        return passwordWithFolderEntityList.stream().map(PasswordWithFolderMapper::forDomain).toList();
    }
}

