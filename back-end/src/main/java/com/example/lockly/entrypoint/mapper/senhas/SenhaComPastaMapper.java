package com.example.lockly.entrypoint.mapper.senhas;

import com.example.lockly.domain.senhas.SenhaComPasta;
import com.example.lockly.entrypoint.dto.senhas.SenhaComPastaDto;
import com.example.lockly.entrypoint.mapper.PastaMapper;
import com.example.lockly.entrypoint.mapper.UsuarioMapper;

import java.util.List;

public abstract class SenhaComPastaMapper {

    public static SenhaComPastaDto paraDto(SenhaComPasta domain) {
        return SenhaComPastaDto.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .conteudo(domain.getConteudo())
                .usuarioDto(UsuarioMapper.paraDto(domain.getUsuario()))
                .pastaDto(PastaMapper.paraDto(domain.getPasta()))
                .build();
    }

    public static SenhaComPasta paraDomain(SenhaComPastaDto dto) {
        return SenhaComPasta.builder()
                .id(dto.id())
                .nome(dto.nome())
                .conteudo(dto.conteudo())
                .usuario(UsuarioMapper.paraDomain(dto.usuarioDto()))
                .pasta(PastaMapper.paraDomain(dto.pastaDto()))
                .build();
    }

    public static List<SenhaComPastaDto> paraDtos(List<SenhaComPasta> senhaComPastaList) {
        return senhaComPastaList.stream().map(SenhaComPastaMapper::paraDto).toList();
    }
}
