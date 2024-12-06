package com.example.lockly.entrypoint.dtos.passwords;

import com.example.lockly.entrypoint.dtos.UserDto;
import lombok.Builder;

@Builder
public record PasswordWithoutFolderDto(Integer id, String name, String content, UserDto userDto) {
}
