package com.example.lockly.controllerLayer.dtos.passwords;

import com.example.lockly.controllerLayer.dtos.UserDto;
import lombok.Builder;

@Builder
public record PasswordWithoutFolderDto(Long id, String nome, String conteudo, UserDto userDto) {
}
