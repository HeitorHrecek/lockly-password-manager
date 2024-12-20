package com.example.lockly.infrastructure.mapper.passwords;

import com.example.lockly.domain.passwords.SenhaComPasta;
import com.example.lockly.infrastructure.mapper.PastaMapper;
import com.example.lockly.infrastructure.mapper.UserMapper;
import com.example.lockly.infrastructure.repository.entities.senha.SenhaComPastaEntity;

import java.util.List;

public abstract class SenhaComPastaMapper {

    public static SenhaComPasta paraDomain(SenhaComPastaEntity entity) {
        return SenhaComPasta.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .conteudo(entity.getConteudo())
                .usuario(UserMapper.paraDomain(entity.getUsuarioEntity()))
                .pasta(PastaMapper.paraDomain(entity.getPastaEntity()))
                .senhaCriptografia(entity.getChaveCriptografia())
                .build();
    }

    public static SenhaComPastaEntity paraEntity(SenhaComPasta domain) {
        return SenhaComPastaEntity.builder()
                .id(domain.getId())
                .nome(domain.getNome())
                .conteudo(domain.getConteudo())
                .usuarioEntity(UserMapper.paraEntity(domain.getUsuario()))
                .pastaEntity(PastaMapper.paraEntity(domain.getPasta()))
                .chaveCriptografia(domain.getSenhaCriptografia())
                .build();
    }

    public static List<SenhaComPasta> paraDomains(List<SenhaComPastaEntity> senhaComPastaEntityList) {
        return senhaComPastaEntityList.stream().map(SenhaComPastaMapper::paraDomain).toList();
    }
}

