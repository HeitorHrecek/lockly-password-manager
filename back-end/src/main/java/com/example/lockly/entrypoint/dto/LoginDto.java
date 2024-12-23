package com.example.lockly.entrypoint.dto;

import lombok.Builder;

@Builder
public record LoginDto(String email, String senha) {
}