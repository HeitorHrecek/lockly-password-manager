package com.example.lockly.dataproviderLayer;

import com.example.lockly.domainLayer.Folder;
import com.example.lockly.repositoryLayer.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class FolderDataProvider {

    private final FolderRepository repository;

    @Autowired
    public FolderDataProvider(FolderRepository repository) {
        this.repository = repository;
    }

    public Folder save(Folder folder) {
        return repository.save(folder);
    }

    public List<Folder> consultAllByUser(Long idUser) {
        return repository.consultAllByUser(idUser);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Optional<Folder> consultById(Long id) {
        return repository.findById(id);
    }
}
