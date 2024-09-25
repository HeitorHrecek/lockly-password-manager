package com.example.lockly.mapper;

import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithFolderDto;
import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;

import java.util.List;

public abstract class PasswordWithFolderMapper {
    public static PasswordWithFolder forDomainFromDto(PasswordWithFolderDto dto) {
        return PasswordWithoutFolder.builder()
                .id(dto.id())
                .name(dto.name())
                .content(dto.content())
                .user(UserMapper.forDomainFromDto(dto.userDto()))
                .build();
    }

    public static PasswordWithFolderDto forDto(PasswordWithFolder domain) {
        return PasswordWithoutFolderDto.builder()
                .id(domain.getId())
                .name(domain.getName())
                .content(domain.getContent())
                .userDto(UserMapper.forDto(domain.getUser()))
                .build();
    }

    public static List<PasswordWithFolderDto> forDtos(List<PasswordWithFolder> passwordWithFolderList) {
        return passwordWithFolderList.stream().map(PasswordWithFolderMapper::forDto).toList();
    }
}
