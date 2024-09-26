package com.example.lockly.repositoryLayer;

import com.example.lockly.repositoryLayer.entities.passwords.PasswordWithFolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PasswordWithFolderRepository extends JpaRepository<PasswordWithFolderEntity, Long> {
    Optional<PasswordWithFolderEntity> findByName(String name);

    List<PasswordWithFolderEntity> findByUser(Long idUser);

    @Query("SELECT pswwf FROM PasswordWithFolder pswwf WHERE pswwf.folderEtity.id = :idFolder")
    List<PasswordWithFolderEntity> findByFolder(Long idFolder);
}
