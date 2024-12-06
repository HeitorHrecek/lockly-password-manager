package com.example.lockly.mapper.passwords;

import com.example.lockly.entrypoint.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import com.example.lockly.mapper.UserMapper;
import com.example.lockly.repositoryLayer.entities.passwords.PasswordWithoutFolderEntity;

import java.util.List;

public abstract class PasswordWithoutFolderMapper {

    public static PasswordWithoutFolder forDomain(PasswordWithoutFolderEntity entity) {
        return PasswordWithoutFolder.builder()
                .id(entity.getId())
                .name(entity.getName())
                .content(entity.getContent())
                .user(UserMapper.forDomain(entity.getUserEntity()))
                .encryptionKey(entity.getEncryptionKey())
                .build();

    }

    public static PasswordWithoutFolderEntity forEnttiy(PasswordWithoutFolder domain) {
        return PasswordWithoutFolderEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .content(domain.getContent())
                .userEntity(UserMapper.forEntity(domain.getUser()))
                .encryptionKey(domain.getEncryptionKey())
                .build();
    }

    public static PasswordWithoutFolderDto forDto(PasswordWithoutFolder domain) {
        return PasswordWithoutFolderDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .content(domain.getContent())
                .userDto(UserMapper.forDto(domain.getUser()))
                .build();
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
