package com.example.lockly.mapper;

import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;

import java.util.List;

public abstract class PasswordWithoutFolderMapper {

    public static PasswordWithoutFolder forDomainFromDto(PasswordWithoutFolderDto dto) {
        return PasswordWithoutFolder.builder()
                .id(dto.id())
                .name(dto.name())
                .content(dto.content())
                .user(UserMapper.forDomainFromDto(dto.userDto()))
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

    public static List<PasswordWithoutFolderDto> forDtos(List<PasswordWithoutFolder> passwordWithFolderList) {
        return passwordWithFolderList.stream().map(PasswordWithoutFolderMapper::forDto).toList();
    }
}
