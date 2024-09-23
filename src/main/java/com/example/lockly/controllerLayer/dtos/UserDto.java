package com.example.lockly.controllerLayer.dtos;

import lombok.Builder;

@Builder
public record UserDto(Long id, String nome, String email, String senha) {}
