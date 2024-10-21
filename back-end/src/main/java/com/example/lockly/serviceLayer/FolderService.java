package com.example.lockly.serviceLayer;

import com.example.lockly.dataproviderLayer.FolderDataProvider;
import com.example.lockly.domainLayer.Folder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FolderService {
    private final FolderDataProvider dataProvider;

    public Folder consultById(Integer id) {
        return dataProvider.consultById(id);
    }
}
