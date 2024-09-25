package com.example.lockly.serviceLayer;

import com.example.lockly.dataproviderLayer.PasswordWithFolderDataProvider;
import com.example.lockly.domainLayer.Folder;
import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import com.example.lockly.serviceLayer.exceptions.NoPasswordFoundException;
import com.example.lockly.serviceLayer.exceptions.PasswordAlreadyRegisteredException;
import com.example.lockly.serviceLayer.exceptions.PasswordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordWithFolderService {

    private final PasswordWithFolderDataProvider dataProvider;
    private final PasswordWithoutService passwordWithoutService;
    private final UserService userService;
    private final FolderService folderService;

    public PasswordWithFolder registerWithFolder(PasswordWithFolder newPassword) {
        Optional<PasswordWithFolder> resultConsult = dataProvider.queryByName(newPassword.getName());
        resultConsult.ifPresent(password -> {
            throw new PasswordAlreadyRegisteredException();
        });

        newPassword.setContent(encryptPassword(newPassword.getContent()));
        newPassword.setUser(userService.consultById(newPassword.getUser().getId()));
        newPassword.setFolder(folderService.consultById(newPassword.getFolder().getId()));

        return dataProvider.save(newPassword);
    }

    public PasswordWithFolder putInFolder(Long idPasswordWithoutFolder, Long idFolder) {
        PasswordWithoutFolder passwordWithoutFolder = passwordWithoutService.consultById(idPasswordWithoutFolder);
        Folder folder = folderService.consultById(idFolder);

        return dataProvider.save(PasswordWithFolder.builder()
                .id(passwordWithoutFolder.getId())
                .name(passwordWithoutFolder.getName())
                .content(passwordWithoutFolder.getContent())
                .user(passwordWithoutFolder.getUser())
                .folder(folder)
                .build()
        );
    }

    public void removePasswordFolder(Long idPasswordWithFolder) {
        PasswordWithFolder passwordWithFolder = consultById(idPasswordWithFolder);
        deleteWithFolder(passwordWithFolder.getId());
        passwordWithoutService.registerWithoutFolder(PasswordWithoutFolder.builder()
                .id(passwordWithFolder.getId())
                .name(passwordWithFolder.getName())
                .content(passwordWithFolder.getContent())
                .user(passwordWithFolder.getUser())
                .build());
    }

    public List<PasswordWithFolder> consultAllWithFolderByUser(Long idUser) {
        List<PasswordWithFolder> passwordWithFolderList = dataProvider.consultAllWithFolderByUser(idUser);
        if (passwordWithFolderList.isEmpty()) {
            throw new NoPasswordFoundException();
        }
        return passwordWithFolderList;
    }

    public PasswordWithFolder queryByNameWithFolder(String name) {
        Optional<PasswordWithFolder> passwordWithFolder = dataProvider.queryByName(name);
        if (passwordWithFolder.isEmpty()) {
            throw new PasswordNotFoundException();
        }
        return passwordWithFolder.get();
    }

    public void deleteWithFolder(Long id) {
        consultById(id);
        dataProvider.delete(id);
    }

    public PasswordWithFolder changeWithFolder(Long idPassword, Long idFolder) {
        Folder newFolder = folderService.consultById(idFolder);
        PasswordWithFolder password = consultById(idPassword);
        password.setFolder(newFolder);

        return dataProvider.save(password);
    }

    public String encryptPassword(String password) {

    }

    private PasswordWithFolder consultById(Long id) {
        Optional<PasswordWithFolder> resultQuery = dataProvider.consultById(id);
        if (resultQuery.isEmpty()) {
            throw new PasswordNotFoundException();
        }
        return resultQuery.get();
    }
}
