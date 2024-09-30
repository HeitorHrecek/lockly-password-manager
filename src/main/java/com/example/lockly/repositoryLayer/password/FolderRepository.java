package com.example.lockly.repositoryLayer.password;

import com.example.lockly.repositoryLayer.entities.FolderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<FolderEntity, Integer> {
}
