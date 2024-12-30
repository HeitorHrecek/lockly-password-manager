package com.example.lockly.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record PastaDto(
        @JsonProperty("id")
        Integer id,

        @NotBlank(message = "O nome é obrigatório")
        @JsonProperty("nome")
        String nome,

        @JsonProperty("usuarioDto")
        UsuarioDto usuarioDto) {
}
