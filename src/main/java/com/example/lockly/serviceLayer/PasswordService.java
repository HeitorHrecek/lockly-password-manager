package com.example.lockly.serviceLayer;

import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import com.example.lockly.serviceLayer.passwords.EncryptService;
import com.example.lockly.serviceLayer.passwords.PasswordWithFolderService;
import com.example.lockly.serviceLayer.passwords.PasswordWithoutFolderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordService {

    private final PasswordWithoutFolderService passwordWithoutFolderService;
    private final PasswordWithFolderService passwordWithFolderService;
    private final EncryptService encryptService;

    public PasswordWithoutFolder decryptWithoutFolder(Long id) {
        PasswordWithoutFolder result = passwordWithoutFolderService.consultById(id);
        result.setContent(encryptService.decrypt(result.getContent()));
        return result;
    }

    public PasswordWithFolder decryptWithFolder(Long id) {
        PasswordWithFolder result = passwordWithFolderService.consultById(id);
        result.setContent(encryptService.decrypt(result.getContent()));
        return result;
    }
}
