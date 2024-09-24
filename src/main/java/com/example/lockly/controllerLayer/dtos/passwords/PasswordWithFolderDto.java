package com.example.lockly.controllerLayer.dtos.passwords;

import com.example.lockly.controllerLayer.dtos.FolderDto;
import com.example.lockly.controllerLayer.dtos.UserDto;
import com.example.lockly.domainLayer.Folder;
import com.example.lockly.domainLayer.User;
import lombok.Builder;

@Builder
public record PasswordWithFolderDto(Long id, String name, String content, UserDto userDto, FolderDto folderDto) {
}
