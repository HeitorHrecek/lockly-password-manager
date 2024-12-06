package com.example.lockly.entrypoint.dtos;

import lombok.Builder;

@Builder
public record PasswordFolderIdsDto(Integer idPassword, Integer idFolder) {}
