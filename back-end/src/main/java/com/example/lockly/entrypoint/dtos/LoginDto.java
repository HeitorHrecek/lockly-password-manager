package com.example.lockly.entrypoint.dtos;

import lombok.Builder;

@Builder
public record LoginDto(String email, String password) {}