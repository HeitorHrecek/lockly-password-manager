package com.example.lockly.entrypoint.dto.senhas;

import com.example.lockly.entrypoint.dto.UsuarioDto;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record SenhaSemPastaDto(

        @JsonProperty("id")
        Integer id,

        @NotBlank(message = "O nome é obrigatório")
        @JsonProperty("nome")
        String nome,

        @NotBlank(message = "O conteúdo é obrigatório")
        @JsonProperty("conteudo")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).+$",
                message = "A senha deve conter pelo menos uma letra, um número e um caractere especial.")
        String conteudo,

        @JsonProperty("usuarioDto")
        UsuarioDto usuarioDto) {
}
