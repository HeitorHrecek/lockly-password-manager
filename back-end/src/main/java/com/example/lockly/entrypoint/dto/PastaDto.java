package com.example.lockly.entrypoint.dto;

import lombok.Builder;

@Builder
public record PastaDto(Integer id, String nome, UsuarioDto usuarioDto) {
}
