package com.example.lockly.entrypoint.dto.senhas;

import com.example.lockly.entrypoint.dto.PastaDto;
import com.example.lockly.entrypoint.dto.UsuarioDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record SenhaComPastaDto(

        @JsonProperty("id")
        Integer id,

        @NotBlank(message = "O nome é obrigatório")
        @JsonProperty("nome")
        String nome,

        @NotBlank(message = "O conteúdo é obrigatório")
        @JsonProperty("conteudo")
        String conteudo,
        UsuarioDto usuarioDto,
        PastaDto pastaDto) {
}
