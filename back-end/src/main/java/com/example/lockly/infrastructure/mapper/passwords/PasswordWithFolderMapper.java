package com.example.lockly.infrastructure.mapper.passwords;

import com.example.lockly.entrypoint.dto.passwords.SenhaComPastaDto;
import com.example.lockly.domain.passwords.SenhaComPasta;
import com.example.lockly.infrastructure.mapper.FolderMapper;
import com.example.lockly.infrastructure.mapper.UserMapper;
import com.example.lockly.infrastructure.repositoryLayer.entities.passwords.PasswordWithFolderEntity;

import java.util.List;

public abstract class PasswordWithFolderMapper {

    public static SenhaComPasta forDomain(PasswordWithFolderEntity entity) {
        return SenhaComPasta.builder()
                .id(entity.getId())
                .nome(entity.getName())
                .conteudo(entity.getContent())
                .usuario(UserMapper.forDomain(entity.getUserEntity()))
                .pasta(FolderMapper.forDomain(entity.getFolderEntity()))
                .senhaCriptografia(entity.getEncryptionKey())
                .build();
    }

    public static PasswordWithFolderEntity forEntity(SenhaComPasta domain) {
        return PasswordWithFolderEntity.builder()
                .id(domain.getId())
                .name(domain.getNome())
                .content(domain.getConteudo())
                .userEntity(UserMapper.forEntity(domain.getUsuario()))
                .folderEntity(FolderMapper.forEntity(domain.getPasta()))
                .encryptionKey(domain.getSenhaCriptografia())
                .build();
    }

    public static List<SenhaComPasta> forDomains(List<PasswordWithFolderEntity> passwordWithFolderEntityList) {
        return passwordWithFolderEntityList.stream().map(PasswordWithFolderMapper::forDomain).toList();
    }
}

