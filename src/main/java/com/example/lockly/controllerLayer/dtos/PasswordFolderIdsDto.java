package com.example.lockly.controllerLayer.dtos;

import lombok.Builder;

@Builder
public record PasswordFolderIdsDto(Integer idPassword, Integer idFolder) {}
