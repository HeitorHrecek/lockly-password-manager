package com.example.lockly.serviceLayer;

import com.example.lockly.controllerLayer.dtos.FolderDto;
import com.example.lockly.dataproviderLayer.FolderDataProvider;
import com.example.lockly.domainLayer.Folder;
import com.example.lockly.mapper.FolderMapper;
import com.example.lockly.serviceLayer.exceptions.folder.FolderAlreadyRegisteredException;
import com.example.lockly.serviceLayer.exceptions.folder.NoFolderFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FolderService {

    private final FolderDataProvider dataProvider;

    // Método privado para validar e consultar pasta por ID
    private Folder findFolderById(Long id) {
        return dataProvider.consultById(id)
                .orElseThrow(() -> new NoFolderFoundException("Folder not found for ID: " + id));
    }

    // Método register ajustado para verificar URI e duplicidade de nome
    public FolderDto register(FolderDto folderDto) {
        List<Folder> existingFolders = dataProvider.consultAllByUser(folderDto.userDto().id().longValue());

        boolean folderExists = existingFolders.stream()
                .anyMatch(folder -> folder.getName().equalsIgnoreCase(folderDto.name()));

        if (folderExists) {
            throw new FolderAlreadyRegisteredException("Folder already exists with name: " + folderDto.name());
        }

        Folder folder = FolderMapper.forDomainFromDto(folderDto);
        return FolderMapper.forDto(dataProvider.save(folder));
    }

    // Método consultAllByUser com validação de lista vazia no service
    public List<FolderDto> consultAllByUser(Long userId) {
        List<Folder> folders = dataProvider.consultAllByUser(userId);

        if (folders.isEmpty()) {
            throw new NoFolderFoundException("No folders found for user ID: " + userId);
        }

        return folders.stream()
                .map(FolderMapper::forDto)
                .collect(Collectors.toList());
    }

    // Método delete com validação de existência
    public void delete(Long id) {
        Folder folder = findFolderById(id);
        dataProvider.delete(folder.getId().longValue());
    }

    // Método changeName com validação movida para o service
    public FolderDto changeName(FolderDto folderDto, Long id) {
        Folder folder = findFolderById(id);
        folder.setName(folderDto.name());
        return FolderMapper.forDto(dataProvider.save(folder));
    }
}
