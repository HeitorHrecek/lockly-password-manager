package com.example.lockly.entrypoint.mapper;

import com.example.lockly.domain.Pasta;
import com.example.lockly.entrypoint.dto.PastaDto;
import com.example.lockly.infrastructure.mapper.UserMapper;

public abstract class PastaMapper {

    public static Pasta paraDomain(PastaDto dto) {
        return com.example.lockly.domain.Pasta.builder()
                .id(dto.id())
                .nome(dto.nome())
                .usuario(UserMapper.forDomainFromDto(dto.usuarioDto()))
                .build();
    }

    public static PastaDto paraDto(Pasta domain) {
        return PastaDto.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .usuarioDto(UserMapper.forDto(domain.getUsuario()))
                .build();
    }
}
