package com.example.lockly.infrastructure.mapper.passwords;

import com.example.lockly.domain.passwords.SenhaSemPasta;
import com.example.lockly.infrastructure.mapper.UserMapper;
import com.example.lockly.infrastructure.repository.entities.senha.SenhaSemPastaEntity;

public abstract class SenhaSemPastaMapper {

    public static SenhaSemPasta paraDomain(SenhaSemPastaEntity entity) {
        return SenhaSemPasta.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .conteudo(entity.getConteudo())
                .usuario(UserMapper.paraDomain(entity.getUsuarioEntity()))
                .chaveCriptografia(entity.getChaveCriptografia())
                .build();

    }

    public static SenhaSemPastaEntity paraEntity(SenhaSemPasta domain) {
        return SenhaSemPastaEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .conteudo(domain.getConteudo())
                .usuarioEntity(UserMapper.paraEntity(domain.getUsuario()))
                .chaveCriptografia(domain.getChaveCriptografia())
                .build();
    }
}
