package com.example.lockly.controllerLayer.dtos;

import lombok.Builder;

@Builder
public record FolderDto(Integer id, String name, UserDto userDto){}
