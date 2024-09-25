package com.example.lockly.dataproviderLayer;

import com.example.lockly.dataproviderLayer.exceptions.*;
import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.mapper.passwords.PasswordWithFolderMapper;
import com.example.lockly.repositoryLayer.PasswordWithFolderRepository;
import com.example.lockly.repositoryLayer.entities.passwords.PasswordWithFolderEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class PasswordWithFolderDataProvider {

    private final PasswordWithFolderRepository repository;

    public PasswordWithFolder save(PasswordWithFolder newPassword) {
        PasswordWithFolderEntity password = PasswordWithFolderMapper.forEntity(newPassword);

        try {
            password = repository.save(password);
        }catch (Exception exception) {
            log.error("Error saving password.", exception);
            throw new ErrorSavePasswordException(exception.getMessage());
        }

        return PasswordWithFolderMapper.forDomain(password);
    }

    public List<PasswordWithFolder> consultAllWithFolderByUser(Long idUser) {
        List<PasswordWithFolderEntity> result;
        try {
            result = repository.findByUser(idUser);
        }catch (Exception exception) {
            log.error("", exception);
            throw new ErrorSearchPasswordAllById(exception.getMessage());
        }
        return result.stream().map(PasswordWithFolderMapper::forDomain).toList();
    }

    public Optional<PasswordWithFolder> queryByName(String name) {
        Optional<PasswordWithFolderEntity> result;
        try {
            result = repository.findByName(name);
        }catch (Exception exception) {
            log.error("Error when search by name", exception);
            throw new ErrorSearchPasswordByName(exception.getMessage());
        }
        return result.map(PasswordWithFolderMapper::forDomain);
    }

    public void delete(Long id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error deleting password", exception);
            throw new ErrorDeletePassword(exception.getMessage());
        }
    }

    public Optional<PasswordWithFolder> consultById(Long id) {
        Optional<PasswordWithFolderEntity> result;
        try {
            result = repository.findById(id);
        }catch (Exception exception) {
            log.error("Error when querying password by id", exception);
            throw new ErrorQueryPasswordByIdException(exception.getMessage());
        }

        return result.map(PasswordWithFolderMapper::forDomain);
    }
}
