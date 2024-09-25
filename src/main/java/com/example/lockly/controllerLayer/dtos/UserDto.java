package com.example.lockly.controllerLayer.dtos;

import lombok.Builder;

@Builder
public record UserDto(Long id, String name, String email, String password) {}
