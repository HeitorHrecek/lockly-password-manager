package com.example.lockly.controllerLayer.dtos.passwords;

import com.example.lockly.controllerLayer.dtos.FolderDto;
import com.example.lockly.controllerLayer.dtos.UserDto;
import lombok.Builder;

@Builder
public record PasswordWithFolderDto(Long id, String name, String content, UserDto userDto, FolderDto folderDto) {
}
