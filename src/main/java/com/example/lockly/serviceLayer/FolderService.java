package com.example.lockly.serviceLayer;

import com.example.lockly.controllerLayer.dtos.FolderDto;
import com.example.lockly.dataproviderLayer.FolderDataProvider;
import com.example.lockly.mapper.FolderMapper;
import com.example.lockly.domainLayer.Folder;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class FolderService {

    private final FolderDataProvider dataProvider;
    private final FolderMapper mapper;


    public FolderDto register(FolderDto folderDto) {
        Folder folder = mapper.forDomainFromDto(folderDto);
        return mapper.forDto(dataProvider.save(folder));
    }

    public List<FolderDto> consultAllByUser(Long userId) {
        return dataProvider.consultAllByUser(userId).stream()
                .map(FolderMapper::forDto)
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        dataProvider.delete(id);
    }

    public FolderDto changeName(FolderDto folderDto, Long id) {
        Folder folder = dataProvider.consultById(id).orElse(null);
        if (folder != null) {
            folder.setName(folderDto.name());
            return mapper.forDto(dataProvider.save(folder));
        }
        return null;
    }
}
