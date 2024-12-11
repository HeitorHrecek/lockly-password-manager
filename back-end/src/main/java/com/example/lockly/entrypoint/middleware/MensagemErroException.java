package com.example.lockly.entrypoint.middleware;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record MensagemErroException(HttpStatus status, String message) {}
