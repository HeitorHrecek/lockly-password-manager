package com.example.lockly.repositoryLayer.password;

import com.example.lockly.repositoryLayer.entities.passwords.PasswordWithoutFolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PasswordWithoutFolderRepository extends JpaRepository<PasswordWithoutFolderEntity, Integer> {

    @Query("SELECT pswtf FROM PasswordWithoutFolder pswtf WHERE pswtf.userEntity.id = :idUser")
    List<PasswordWithoutFolderEntity> consultAllByUser(Integer idUser);

    Optional<PasswordWithoutFolderEntity> findByName(String name);
}
