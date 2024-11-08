package com.example.lockly.repositoryLayer;

import com.example.lockly.repositoryLayer.entities.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Integer> {
    @Query("SELECT fld FROM Folder fld WHERE fld.userEntity.id = :userId")
    List<FolderEntity> consultAllByUser(Integer userId);
    Optional<FolderEntity> findByName(String name);
}
