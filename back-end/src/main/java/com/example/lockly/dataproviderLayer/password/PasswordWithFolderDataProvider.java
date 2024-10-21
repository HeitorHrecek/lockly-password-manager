package com.example.lockly.dataproviderLayer.password;

import com.example.lockly.dataproviderLayer.exceptions.password.*;
import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.mapper.passwords.PasswordWithFolderMapper;
import com.example.lockly.repositoryLayer.password.PasswordWithFolderRepository;
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
        } catch (Exception exception) {
            log.error("Error saving password with folder.", exception);
            throw new ErrorSavePasswordException(exception.getMessage());
        }

        return PasswordWithFolderMapper.forDomain(password);
    }

    public List<PasswordWithFolder> consultAllByUser(Integer idUser) {
        List<PasswordWithFolderEntity> result;
        try {
            result = repository.findByUser(idUser);
        } catch (Exception exception) {
            log.error("", exception);
            throw new ErrorSearchAllPasswordById(exception.getMessage());
        }
        return PasswordWithFolderMapper.forDomains(result);
    }

    public Optional<PasswordWithFolder> queryByName(String name) {
        Optional<PasswordWithFolderEntity> result;
        try {
            result = repository.findByName(name);
        } catch (Exception exception) {
            log.error("Error when search passsword with folder by name", exception);
            throw new ErrorSearchPasswordByNameException(exception.getMessage());
        }
        return result.map(PasswordWithFolderMapper::forDomain);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            log.error("Error deleting password with folder", exception);
            throw new ErrorDeletePassword(exception.getMessage());
        }
    }

    public Optional<PasswordWithFolder> consultById(Integer id) {
        Optional<PasswordWithFolderEntity> result;
        try {
            result = repository.findById(id);
        } catch (Exception exception) {
            log.error("Error when querying password with folder by id", exception);
            throw new ErrorQueryPasswordByIdException(exception.getMessage());
        }

        return result.map(PasswordWithFolderMapper::forDomain);
    }

    public List<PasswordWithFolder> consultallByFolder(Integer idFolder) {
        List<PasswordWithFolderEntity> result;
        try {
            result = repository.findByFolder(idFolder);
        } catch (Exception exception) {
            log.error("Erro find passwords with folder by id folder");
            throw new ErrorSearchPasswordByFolderException(exception.getMessage());
        }
        return PasswordWithFolderMapper.forDomains(result);
    }
}
