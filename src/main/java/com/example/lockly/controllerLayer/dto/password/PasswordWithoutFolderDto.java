package com.example.lockly.controllerLayer.dto.password;

public class PasswordWithoutFolderDto extends PasswordDto {
    public PasswordWithoutFolderDto(Long id, String name, String content, UserDto user) {
        super(id, name, content, user);
    }
}
