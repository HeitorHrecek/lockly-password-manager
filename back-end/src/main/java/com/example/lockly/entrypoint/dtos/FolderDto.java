package com.example.lockly.entrypoint.dtos;

import lombok.Builder;

@Builder
public record FolderDto(Integer id, String name, UserDto userDto){}
