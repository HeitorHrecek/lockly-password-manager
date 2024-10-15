package com.example.lockly.repositoryLayer;

import com.example.lockly.repositoryLayer.entities.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Long> {
    List<FolderEntity> consultAllByUser(Long userId);
}
