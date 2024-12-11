package com.example.lockly.entrypoint.dtos.passwords;

import com.example.lockly.entrypoint.dtos.PastaDto;
import com.example.lockly.entrypoint.dtos.UsuarioDto;
import lombok.Builder;

@Builder
public record SenhaComPastaDto(Integer id, String nome, String conteudo, UsuarioDto usuarioDto, PastaDto pastaDto) {
}
