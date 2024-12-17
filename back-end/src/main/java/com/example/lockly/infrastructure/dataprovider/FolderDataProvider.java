package com.example.lockly.infrastructure.dataprovider;

import com.example.lockly.dataproviderLayer.exceptions.folder.*;
import com.example.lockly.domain.Pasta;
import com.example.lockly.infrastructure.dataprovider.exceptions.pasta.*;
import com.example.lockly.infrastructure.mapper.FolderMapper;
import com.example.lockly.infrastructure.repositoryLayer.FolderRepository;
import com.example.lockly.infrastructure.repositoryLayer.entities.FolderEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Component
public class FolderDataProvider {

    private final FolderRepository repository;

    public Pasta save(Pasta pasta) {
        FolderEntity entity = FolderMapper.forEntity(pasta);
        try {
            FolderEntity savedEntity = repository.save(entity);
            return FolderMapper.forDomain(savedEntity);
        } catch (Exception exception) {
            log.error("Error while saving the folder.", exception);
            throw new ErroSalvarPastaException(exception.getMessage());
        }
    }

    public List<Pasta> consultAllByUser(Integer idUser) {
        try {
            List<FolderEntity> entities = repository.consultAllByUser(idUser);
            return entities.stream()
                    .map(FolderMapper::forDomain)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            log.error("Error while consulting folders by user", exception);
            throw new ErroAoListarPastasPorUsuarioException(exception.getMessage());
        }
    }

    public Optional<Pasta> consultById(Integer id) {
        try {
            Optional<FolderEntity> entity = repository.findById(id);
            return entity.map(FolderMapper::forDomain);
        } catch (Exception exception) {
            log.error("Error while consulting folder by", exception);
            throw new ErroConsultarPastaPorIdException(exception.getMessage());
        }
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error while deleting folder" + id, exception);
            throw new ErroDeletarPastaException(exception.getMessage());
        }
    }



    public Optional<Pasta> consultByName(String name) {
        Optional<FolderEntity> folderEntity;

        try {
            folderEntity = repository.findByName(name);
        } catch (Exception exception) {
            log.error("Error while consulting folder by name", exception);
            throw new ErroConsultarPastaPorNomeException(exception.getMessage());
        }

        return folderEntity.map(FolderMapper::forDomain);
    }
}

