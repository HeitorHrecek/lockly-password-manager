package com.example.lockly.entrypoint.mapper;

import com.example.lockly.domain.Usuario;
import com.example.lockly.entrypoint.dto.UsuarioDto;

public abstract class UsuarioMapper {
    public static UsuarioDto paraDto(Usuario domain) {
        return UsuarioDto.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .email(domain.getEmail())
                .senha(domain.getSenha())
                .build();
    }

    public static Usuario paraDomain(UsuarioDto dto) {
        return Usuario.builder()
                .id(dto.id())
                .nome(dto.nome())
                .email(dto.email())
                .senha(dto.senha())
                .build();
    }
}
