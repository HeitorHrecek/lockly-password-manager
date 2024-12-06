package com.example.lockly.mapper;

import com.example.lockly.entrypoint.dtos.FolderDto;
import com.example.lockly.domainLayer.Folder;
import com.example.lockly.repositoryLayer.entities.FolderEntity;

public abstract class FolderMapper {

    public static Folder forDomain(FolderEntity entity) {
        return Folder.builder()
                .id(entity.getId())
                .name(entity.getName())
                .user(UserMapper.forDomain(entity.getUserEntity()))
                .build();
    }

    public static FolderEntity forEntity(Folder domain) {
        return FolderEntity.builder()
                .id(domain.getId())
                .name(domain.getName())
                .userEntity(UserMapper.forEntity(domain.getUser()))
                .build();
    }

    public static FolderDto forDto(Folder domain) {
        return FolderDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .userDto(UserMapper.forDto(domain.getUser()))
                .build();
    }

    public static Folder forDomainFromDto(FolderDto dto) {
        return Folder.builder()
                .id(dto.id())
                .name(dto.name())
                .user(UserMapper.forDomainFromDto(dto.userDto()))
                .build();
    }
}
