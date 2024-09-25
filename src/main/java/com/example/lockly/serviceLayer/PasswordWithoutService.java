package com.example.lockly.serviceLayer;

import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PasswordWithoutService {
    public PasswordWithoutFolder registerWithoutFolder(PasswordWithoutFolder forDomainFromDto) {
    }

    public List<PasswordWithoutFolder> consultAllWithoutFolderByUser(Long idUser) {
    }

    public PasswordWithoutFolder queryByNameWithoutFolder(String name) {
    }

    public void deleteWithoutFolder(Long id) {
    }

    public PasswordWithoutFolder changeNoFolder(PasswordWithoutFolder forDomainFromDto, Long id) {
    }

    public PasswordWithoutFolder consultById(Long idPasswordWithoutFolder) {
    }
}
