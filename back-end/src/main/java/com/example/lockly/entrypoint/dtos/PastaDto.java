package com.example.lockly.entrypoint.dtos;

import lombok.Builder;

@Builder
public record PastaDto(Integer id, String nome, UsuarioDto usuarioDto){}
