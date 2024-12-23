package com.example.lockly.entrypoint.controller.middleware;

import lombok.Builder;
import org.springframework.http.HttpStatus;

@Builder
public record MensagemErroException(HttpStatus status, String mensagem) {
}
