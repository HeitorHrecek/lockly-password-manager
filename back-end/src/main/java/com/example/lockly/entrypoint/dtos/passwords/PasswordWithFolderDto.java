package com.example.lockly.entrypoint.dtos.passwords;

import com.example.lockly.entrypoint.dtos.FolderDto;
import com.example.lockly.entrypoint.dtos.UserDto;
import lombok.Builder;

@Builder
public record PasswordWithFolderDto(Integer id, String name, String content, UserDto userDto, FolderDto folderDto) {
}
