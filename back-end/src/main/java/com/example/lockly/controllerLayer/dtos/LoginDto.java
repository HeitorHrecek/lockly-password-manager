package com.example.lockly.controllerLayer.dtos;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record LoginDto(String email, String password) {}