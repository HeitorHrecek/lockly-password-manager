package com.example.lockly.serviceLayer.passwords;

import com.example.lockly.dataproviderLayer.PasswordWithFolderDataProvider;
import com.example.lockly.domainLayer.Folder;
import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import com.example.lockly.serviceLayer.FolderService;
import com.example.lockly.serviceLayer.UserService;
import com.example.lockly.serviceLayer.exceptions.password.NoPasswordFoundException;
import com.example.lockly.serviceLayer.exceptions.password.PasswordAlreadyRegisteredException;
import com.example.lockly.serviceLayer.exceptions.password.PasswordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordWithFolderService {

    private final PasswordWithFolderDataProvider dataProvider;
    private final PasswordWithoutFolderService passwordWithoutFolderService;
    private final UserService userService;
    private final FolderService folderService;
    private final EncryptService encryptService;

    public PasswordWithFolder register(PasswordWithFolder newPassword) {
        Optional<PasswordWithFolder> resultConsult = dataProvider.queryByName(newPassword.getName());
        resultConsult.ifPresent(password -> {
            throw new PasswordAlreadyRegisteredException();
        });

        newPassword.setContent(encryptService.encrypt(newPassword.getContent()));
        newPassword.setUser(userService.consultById(newPassword.getUser().getId()));
        newPassword.setFolder(folderService.consultById(newPassword.getFolder().getId()));

        return dataProvider.save(newPassword);
    }

    public PasswordWithFolder putInFolder(Integer idPasswordWithoutFolder, Integer idFolder) {
        PasswordWithoutFolder passwordWithoutFolder = passwordWithoutFolderService.consultById(idPasswordWithoutFolder);
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

    public void removePasswordFolder(Integer idPasswordWithFolder) {
        PasswordWithFolder passwordWithFolder = consultById(idPasswordWithFolder);
        deleteWithFolder(passwordWithFolder.getId());
        passwordWithoutFolderService.register(PasswordWithoutFolder.builder()
                .id(passwordWithFolder.getId())
                .name(passwordWithFolder.getName())
                .content(passwordWithFolder.getContent())
                .user(passwordWithFolder.getUser())
                .build());
    }

    public List<PasswordWithFolder> consultAllByUser(Integer idUser) {
        List<PasswordWithFolder> passwordWithFolderList = dataProvider.consultAllByUser(idUser);
        if (passwordWithFolderList.isEmpty()) {
            throw new NoPasswordFoundException();
        }
        return passwordWithFolderList;
    }

    public PasswordWithFolder queryByName(String name) {
        Optional<PasswordWithFolder> passwordWithFolder = dataProvider.queryByName(name);
        if (passwordWithFolder.isEmpty()) {
            throw new PasswordNotFoundException();
        }
        return passwordWithFolder.get();
    }

    public void deleteWithFolder(Integer id) {
        consultById(id);
        dataProvider.delete(id);
    }

    public PasswordWithFolder changeFolder(Integer idPassword, Integer idFolder) {
        Folder newFolder = folderService.consultById(idFolder);
        PasswordWithFolder password = consultById(idPassword);
        password.setFolder(newFolder);

        return dataProvider.save(password);
    }

    public PasswordWithFolder change(PasswordWithFolder newData, Integer idPassword) {
        PasswordWithFolder existingPassword = consultById(idPassword);
        if(newData.getUser() != null)
            existingPassword.setUser(userService.consultById(newData.getUser().getId()));
        if(newData.getFolder() != null)
            existingPassword.setFolder(folderService.consultById(newData.getFolder().getId()));

        existingPassword.setData(newData);
        return dataProvider.save(existingPassword);
    }

    public PasswordWithFolder consultById(Integer id) {
        Optional<PasswordWithFolder> resultQuery = dataProvider.consultById(id);
        if (resultQuery.isEmpty()) {
            throw new PasswordNotFoundException();
        }
        return resultQuery.get();
    }

    public List<PasswordWithFolder> consultAllByFolder(Integer idFolder) {
        List<PasswordWithFolder> result = dataProvider.consultallByFolder(idFolder);
        if(result.isEmpty()) {
            throw new NoPasswordFoundException();
        }
        return result;
    }
}
