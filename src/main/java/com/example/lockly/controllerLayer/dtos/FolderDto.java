package com.example.lockly.controllerLayer.dtos;

import lombok.Builder;

@Builder
public record FolderDto(Long id, String nome, UserDto userDto){}
