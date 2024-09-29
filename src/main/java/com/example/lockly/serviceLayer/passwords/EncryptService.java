package com.example.lockly.serviceLayer.passwords;

import org.springframework.stereotype.Service;

@Service
public class EncryptService {
    public String encryptLogin(String password){
        return encryptPassword(password);
    }

    private static String encryptPassword(String plainText){
        return plainText;
    }
}
