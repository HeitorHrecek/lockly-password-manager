package com.example.lockly.infrastructure.mapper.passwords;

import com.example.lockly.entrypoint.dto.passwords.SenhaSemPastaDto;
import com.example.lockly.domain.passwords.SenhaSemPasta;
import com.example.lockly.infrastructure.mapper.UserMapper;
import com.example.lockly.infrastructure.repositoryLayer.entities.passwords.PasswordWithoutFolderEntity;

import java.util.List;

public abstract class PasswordWithoutFolderMapper {

    public static SenhaSemPasta forDomain(PasswordWithoutFolderEntity entity) {
        return SenhaSemPasta.builder()
                .id(entity.getId())
                .nome(entity.getName())
                .conteudo(entity.getContent())
                .usuario(UserMapper.forDomain(entity.getUserEntity()))
                .chaveCriptografia(entity.getEncryptionKey())
                .build();

    }

    public static PasswordWithoutFolderEntity forEnttiy(SenhaSemPasta domain) {
        return PasswordWithoutFolderEntity.builder()
                .id(domain.getId())
                .name(domain.getNome())
                .content(domain.getConteudo())
                .userEntity(UserMapper.forEntity(domain.getUsuario()))
                .encryptionKey(domain.getChaveCriptografia())
                .build();
    }

    public static SenhaSemPastaDto forDto(SenhaSemPasta domain) {
        return SenhaSemPastaDto.builder()
                .id(domain.getId())
                .name(domain.getNome())
                .content(domain.getConteudo())
                .userDto(UserMapper.forDto(domain.getUsuario()))
                .build();
    }

    public static SenhaSemPasta forDomainFromDto(SenhaSemPastaDto dto) {
        return SenhaSemPasta.builder()
                .id(dto.id())
                .nome(dto.name())
                .conteudo(dto.content())
                .usuario(UserMapper.forDomainFromDto(dto.userDto()))
                .build();
    }

    public static List<SenhaSemPastaDto> forDtos(List<SenhaSemPasta> passwordWithFolderList) {
        return passwordWithFolderList.stream().map(PasswordWithoutFolderMapper::forDto).toList();
    }
}
