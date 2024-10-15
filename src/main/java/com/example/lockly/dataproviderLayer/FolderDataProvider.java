package com.example.lockly.dataproviderLayer;

import com.example.lockly.dataproviderLayer.exceptions.folder.ConsultAllFolderByUserErroException;
import com.example.lockly.dataproviderLayer.exceptions.folder.DeleteFolderErroException;
import com.example.lockly.dataproviderLayer.exceptions.folder.FolderSaveErrorException;
import com.example.lockly.dataproviderLayer.exceptions.folder.FolderConsultByIdErrorException;
import com.example.lockly.domainLayer.Folder;
import com.example.lockly.mapper.FolderMapper;
import com.example.lockly.repositoryLayer.FolderRepository;
import com.example.lockly.repositoryLayer.entities.FolderEntity;
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

    public Folder save(Folder folder) {
        FolderEntity entity = FolderMapper.forEntity(folder);
        try {
            FolderEntity savedEntity = repository.save(entity);
            return FolderMapper.forDomain(savedEntity);
        } catch (Exception exception) {
            log.error("Error while saving the folder.", exception);
            throw new FolderSaveErrorException(exception.getMessage());
        }
    }

    public List<Folder> consultAllByUser(Long idUser) {
        try {
            List<FolderEntity> entities = repository.consultAllByUser(idUser);
            return entities.stream()
                    .map(FolderMapper::forDomain)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            log.error("Error while consulting folders by user ID: " + idUser, exception);
            throw new ConsultAllFolderByUserErroException(exception.getMessage());
        }
    }

    public Optional<Folder> consultById(Long id) {
        try {
            Optional<FolderEntity> entity = repository.findById(id);
            return entity.map(FolderMapper::forDomain);
        } catch (Exception exception) {
            log.error("Error while consulting folder by ID: " + id, exception);
            throw new FolderConsultByIdErrorException(exception.getMessage());
        }
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error while deleting folder with ID: " + id, exception);
            throw new DeleteFolderErroException(exception.getMessage());
        }
    }
}
