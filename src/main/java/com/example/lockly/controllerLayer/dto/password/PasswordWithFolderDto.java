package com.example.lockly.controllerLayer.dto.password;

public class PasswordWithFolderDto extends PasswordDto {
    public PasswordWithFolderDto(Long id, String name, String content, UserDto user, Folder folder) {
        super(id, name, content, user);
        this.folder = folder;
    }

    private Folder folder;
}
