package com.example.lockly.controllerLayer.dtos.passwords;

import com.example.lockly.controllerLayer.dtos.UserDto;
import lombok.Builder;

@Builder
public record PasswordWithoutFolderDto(Long id, String name, String content, UserDto userDto) {
}
