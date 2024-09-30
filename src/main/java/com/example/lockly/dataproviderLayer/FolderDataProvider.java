package com.example.lockly.dataproviderLayer;

import com.example.lockly.domainLayer.Folder;
import com.example.lockly.mapper.FolderMapper;
import com.example.lockly.repositoryLayer.password.FolderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FolderDataProvider {
    private final FolderRepository repository;


    public Folder consultById(Integer id) {
        return FolderMapper.forDomain(repository.findById(id).get());
    }
}
