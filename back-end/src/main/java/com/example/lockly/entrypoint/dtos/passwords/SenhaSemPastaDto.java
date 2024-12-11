package com.example.lockly.entrypoint.dtos.passwords;

import com.example.lockly.entrypoint.dtos.UsuarioDto;
import lombok.Builder;

@Builder
public record SenhaSemPastaDto(Integer id, String nome, String conteudo, UsuarioDto usuarioDto) {
}
