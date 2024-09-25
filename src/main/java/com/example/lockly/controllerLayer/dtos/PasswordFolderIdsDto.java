package com.example.lockly.controllerLayer.dtos;

import lombok.Builder;

@Builder
public record PasswordFolderIdsDto(Long idPassword, Long idFolder) {}
