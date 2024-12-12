package com.example.lockly.application.usecases;
import com.example.lockly.entrypoint.dto.PastaDto;
import com.example.lockly.dataproviderLayer.FolderDataProvider;
import com.example.lockly.domainLayer.Folder;
import com.example.lockly.domainLayer.User;
import com.example.lockly.mapper.FolderMapper;
import com.example.lockly.application.exceptions.pasta.PastaNaoEncontradaException;
import com.example.lockly.application.exceptions.pasta.PastaJaCadastradaException;
import com.example.lockly.application.exceptions.pasta.NenhumaPastaEncontradaException;
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
                .orElseThrow(() -> new NenhumaPastaEncontradaException("Folder not found"));
    }

    public PastaDto register(PastaDto pastaDto) {
        List<Folder> existingFolders = dataProvider.consultAllByUser(pastaDto.userDto().id());

        boolean folderExists = existingFolders.stream()
                .anyMatch(folder -> folder.getName().equalsIgnoreCase(pastaDto.name()));

        if (folderExists) {
            throw new PastaJaCadastradaException("Folder already exists with that name");
        }

        Folder folder = FolderMapper.forDomainFromDto(pastaDto);
        User user = userService.consultById(pastaDto.userDto().id());
        folder.setUser(user);
        return FolderMapper.forDto(dataProvider.save(folder));
    }

    public List<PastaDto> consultAllByUser(Integer userId) {
        List<Folder> folders = dataProvider.consultAllByUser(userId);

        if (folders.isEmpty()) {
            throw new NenhumaPastaEncontradaException("No folders found");
        }

        return folders.stream()
                .map(FolderMapper::forDto)
                .collect(Collectors.toList());
    }

    public void delete(Integer id) {
        Folder folder = findFolderById(id);
        dataProvider.delete(folder.getId());
    }

    public PastaDto changeName(String nome, Integer id) {
        Folder folder = findFolderById(id);
        folder.setName(nome);
        return FolderMapper.forDto(dataProvider.save(folder));
    }

    public PastaDto consultByName(String name) {
        Optional<Folder> folder = dataProvider.consultByName(name);

        if(folder.isEmpty()) {
            throw new PastaNaoEncontradaException("Not found folder");
        }

        return FolderMapper.forDto(folder.get());
    }
}
