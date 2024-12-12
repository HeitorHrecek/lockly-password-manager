package com.example.lockly.mapper.passwords;

import com.example.lockly.entrypoint.dto.passwords.SenhaComPastaDto;
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
                .folder(FolderMapper.forDomain(entity.getFolderEntity()))
                .encryptionKey(entity.getEncryptionKey())
                .build();
    }

    public static PasswordWithFolderEntity forEntity(PasswordWithFolder domain) {
        return PasswordWithFolderEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .content(domain.getContent())
                .userEntity(UserMapper.forEntity(domain.getUser()))
                .folderEntity(FolderMapper.forEntity(domain.getFolder()))
                .encryptionKey(domain.getEncryptionKey())
                .build();
    }

    public static SenhaComPastaDto forDto(PasswordWithFolder domain) {
        return SenhaComPastaDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .content(domain.getContent())
                .userDto(UserMapper.forDto(domain.getUser()))
                .folderDto(FolderMapper.forDto(domain.getFolder()))
                .build();
    }

    public static PasswordWithFolder forDomainFromDto(SenhaComPastaDto dto) {
        return PasswordWithFolder.builder()
                .id(dto.id())
                .name(dto.name())
                .content(dto.content())
                .user(UserMapper.forDomainFromDto(dto.userDto()))
                .folder(FolderMapper.forDomainFromDto(dto.folderDto()))
                .build();
    }

    public static List<SenhaComPastaDto> forDtos(List<PasswordWithFolder> passwordWithFolderList) {
        return passwordWithFolderList.stream().map(PasswordWithFolderMapper::forDto).toList();
    }

    public static List<PasswordWithFolder> forDomains(List<PasswordWithFolderEntity> passwordWithFolderEntityList) {
        return passwordWithFolderEntityList.stream().map(PasswordWithFolderMapper::forDomain).toList();
    }
}

