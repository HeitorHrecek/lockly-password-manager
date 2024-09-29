package com.example.lockly.repositoryLayer;

import com.example.lockly.repositoryLayer.entities.FolderEntity;
import com.example.lockly.repositoryLayer.entities.pfk.FolderId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Integer> {
}
