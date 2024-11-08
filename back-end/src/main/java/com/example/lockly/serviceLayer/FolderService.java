package com.example.lockly.serviceLayer;

import com.example.lockly.controllerLayer.dtos.FolderDto;
import com.example.lockly.dataproviderLayer.FolderDataProvider;
import com.example.lockly.domainLayer.Folder;
import com.example.lockly.domainLayer.User;
import com.example.lockly.mapper.FolderMapper;
import com.example.lockly.serviceLayer.exceptions.folder.NotFoudFolderException;
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
    private final UserService userService;

    public Folder findFolderById(Integer id) {
        return dataProvider.consultById(id)
                .orElseThrow(() -> new NoFolderFoundException("Folder not found"));
    }

    public FolderDto register(FolderDto folderDto) {
        List<Folder> existingFolders = dataProvider.consultAllByUser(folderDto.userDto().id());

        boolean folderExists = existingFolders.stream()
                .anyMatch(folder -> folder.getName().equalsIgnoreCase(folderDto.name()));

        if (folderExists) {
            throw new FolderAlreadyRegisteredException("Folder already exists with that name");
        }

        Folder folder = FolderMapper.forDomainFromDto(folderDto);
        User user = userService.consultById(folderDto.userDto().id());
        folder.setUser(user);
        return FolderMapper.forDto(dataProvider.save(folder));
    }

    public List<FolderDto> consultAllByUser(Integer userId) {
        List<Folder> folders = dataProvider.consultAllByUser(userId);

        if (folders.isEmpty()) {
            throw new NoFolderFoundException("No folders found");
        }

        return folders.stream()
                .map(FolderMapper::forDto)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        Folder folder = findFolderById(id);
        dataProvider.delete(folder.getId());
    }

    public FolderDto changeName(FolderDto folderDto, Integer id) {
        Folder folder = findFolderById(id);
        folder.setName(folderDto.name());
        return FolderMapper.forDto(dataProvider.save(folder));
    }

    public FolderDto consultByName(String name) {
        Optional<Folder> folder = dataProvider.consultByName(name);

        if(folder.isEmpty()) {
            throw new NotFoudFolderException("Not found folder");
        }

        return FolderMapper.forDto(folder.get());
    }
}
