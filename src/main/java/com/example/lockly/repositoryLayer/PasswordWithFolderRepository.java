package com.example.lockly.repositoryLayer;

import com.example.lockly.repositoryLayer.entities.passwords.PasswordWithFolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PasswordWithFolderRepository extends JpaRepository<PasswordWithFolderEntity, Long> {
    Optional<PasswordWithFolderEntity> findByName(String name);

    List<PasswordWithFolderEntity> findByUser(Long idUser);
}
