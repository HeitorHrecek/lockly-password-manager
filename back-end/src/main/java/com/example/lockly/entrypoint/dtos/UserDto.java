package com.example.lockly.entrypoint.dtos;

import lombok.Builder;

@Builder
public record UserDto(Integer id, String name, String email, String password) {}
