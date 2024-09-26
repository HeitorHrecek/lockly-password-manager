package com.example.lockly.serviceLayer.passwords;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

@Service
public class EncryptService {

    private final TextEncryptor encryptor;

    @Autowired
    public EncryptService(TextEncryptor encryptor) {
        this.encryptor = encryptor;
    }


    public String encrypt(String password) {
        return encryptor.encrypt(password);
    }

    public String decrypt(String password) {
        return encryptor.decrypt(password);
    }
}
