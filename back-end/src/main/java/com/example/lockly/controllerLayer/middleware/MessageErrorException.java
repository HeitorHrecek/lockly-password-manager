package com.example.lockly.controllerLayer.middleware;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record MessageErrorException(HttpStatus status, String message) {}
