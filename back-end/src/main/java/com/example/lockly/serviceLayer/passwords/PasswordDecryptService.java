package com.example.lockly.serviceLayer.passwords;

import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordDecryptService {

    private final PasswordWithoutFolderService passwordWithoutFolderService;
    private final PasswordWithFolderService passwordWithFolderService;
    private final EncryptService encryptService;

    public PasswordWithoutFolder decryptWithoutFolder(Integer id) {
        PasswordWithoutFolder result = passwordWithoutFolderService.consultById(id);
        result.setContent(encryptService.decrypt(result.getContent(), result.getEncryptionKey()));
        return result;
    }

    public PasswordWithFolder decryptWithFolder(Integer id) {
        PasswordWithFolder result = passwordWithFolderService.consultById(id);
        result.setContent(encryptService.decrypt(result.getContent(), result.getEncryptionKey()));
        return result;
    }
}
