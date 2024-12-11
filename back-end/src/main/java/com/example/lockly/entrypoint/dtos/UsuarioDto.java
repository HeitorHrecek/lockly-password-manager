package com.example.lockly.entrypoint.dtos;

import lombok.Builder;

@Builder
public record UsuarioDto(Integer id, String nome, String email, String senha) {}
