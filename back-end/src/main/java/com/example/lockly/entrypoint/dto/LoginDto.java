package com.example.lockly.entrypoint.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record LoginDto(

        @NotBlank(message = "O email deve ser obrigatório")
        @Email(message = "O email deve ser válido")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "A senha deve ser obrigatória")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).+$",
                message = "A senha deve conter pelo menos uma letra, um número e um caractere especial.")
        @JsonProperty("senha")
        String senha) {
}