package com.example.lockly.entrypoint.mapper.senha;

import com.example.lockly.domain.passwords.SenhaSemPasta;
import com.example.lockly.entrypoint.dto.passwords.SenhaSemPastaDto;
import com.example.lockly.entrypoint.mapper.UsuarioMapper;

import java.util.List;

public abstract class SenhaSemPastaMapper {
    public static SenhaSemPastaDto paraDto(SenhaSemPasta domain) {
        return SenhaSemPastaDto.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .conteudo(domain.getConteudo())
                .usuarioDto(UsuarioMapper.paraDto(domain.getUsuario()))
                .build();
    }

    public static SenhaSemPasta paraDomain(SenhaSemPastaDto dto) {
        return SenhaSemPasta.builder()
                .id(dto.id())
                .nome(dto.nome())
                .conteudo(dto.conteudo())
                .usuario(UsuarioMapper.paraDomain(dto.usuarioDto()))
                .build();
    }

    public static List<SenhaSemPastaDto> paraDtos(List<SenhaSemPasta> passwordWithFolderList) {
        return passwordWithFolderList.stream().map(SenhaSemPastaMapper::paraDto).toList();
    }
}
