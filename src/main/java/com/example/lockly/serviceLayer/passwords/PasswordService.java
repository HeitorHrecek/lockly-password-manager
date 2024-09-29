package com.example.lockly.serviceLayer.passwords;

import com.example.lockly.domainLayer.User;
import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordService {

    private final PasswordWithoutFolderService passwordWithoutFolderService;
    private final PasswordWithFolderService passwordWithFolderService;
    private final EncryptService encryptService;
    private final PasswordEncoder passwordEncoder;

    public PasswordWithoutFolder decryptWithoutFolder(Integer id) {
        PasswordWithoutFolder result = passwordWithoutFolderService.consultById(id);
        result.setContent(encryptService.decrypt(result.getContent()));
        return result;
    }

    public PasswordWithFolder decryptWithFolder(Integer id) {
        PasswordWithFolder result = passwordWithFolderService.consultById(id);
        result.setContent(encryptService.decrypt(result.getContent()));
        return result;
    }

    public boolean validatePassword(String password, User user){
        return passwordEncoder.matches(password, user.getPassword());
    }
}
