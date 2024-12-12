package com.example.lockly.entrypoint.dto.passwords;

import com.example.lockly.entrypoint.dto.UsuarioDto;
import lombok.Builder;

@Builder
public record SenhaSemPastaDto(Integer id, String nome, String conteudo, UsuarioDto usuarioDto) {
}
