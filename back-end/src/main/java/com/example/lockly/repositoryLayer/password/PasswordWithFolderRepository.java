package com.example.lockly.repositoryLayer.password;

import com.example.lockly.repositoryLayer.entities.passwords.PasswordWithFolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PasswordWithFolderRepository extends JpaRepository<PasswordWithFolderEntity, Integer> {
    @Query("SELECT pswwf FROM PasswordWithFolder pswwf WHERE pswwf.name = :name")
    Optional<PasswordWithFolderEntity> findByName(@Param("name") String name);

    @Query("SELECT pswwf FROM PasswordWithFolder pswwf WHERE pswwf.userEntity.id = :idUser")
    List<PasswordWithFolderEntity> findByUser(@Param("idUser") Integer idUser);

    @Query("SELECT pswwf FROM PasswordWithFolder pswwf WHERE pswwf.folderEntity.id = :idFolder")
    List<PasswordWithFolderEntity> findByFolder(@Param("idFolder") Integer idFolder);

}
