package com.example.lockly.config;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.util.text.TextEncryptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EncryptionConfig {
    @Bean
    public TextEncryptor textEncryptor() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        // Defina a senha para a criptografia
        encryptor.setPassword("sua-senha-secreta-aqui");
        // Defina o algoritmo desejado, por exemplo, "PBEWithMD5AndDES"
        encryptor.setAlgorithm("PBEWithMD5AndDES");
        return encryptor;
    }
}
