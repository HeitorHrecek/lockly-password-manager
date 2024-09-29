package com.example.lockly.serviceLayer.passwords;

public class EncryptService {
    public String encryptLogin(String password){
        return encryptPassword(password);
    }

    private static String encryptPassword(String plainText){
        return plainText;
    }
}
