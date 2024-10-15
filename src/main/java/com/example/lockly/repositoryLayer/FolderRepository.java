package com.example.lockly.repositoryLayer;

import com.example.lockly.domainLayer.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Long> {
    List<Folder> consultAllByUser(Long userId);
}