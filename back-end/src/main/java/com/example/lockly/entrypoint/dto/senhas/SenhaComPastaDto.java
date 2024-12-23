package com.example.lockly.entrypoint.dto.senhas;

import com.example.lockly.entrypoint.dto.PastaDto;
import com.example.lockly.entrypoint.dto.UsuarioDto;
import lombok.Builder;

@Builder
public record SenhaComPastaDto(
        
        Integer id,
        String nome,
        String conteudo,
        UsuarioDto usuarioDto,
        PastaDto pastaDto) {
}
