package com.example.lockly.domainLayer.passwords;

import com.example.lockly.domainLayer.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.crypto.SecretKey;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasswordWithoutFolder {
    private Integer id;
    private String name;
    private String content;
    private User user;
    private SecretKey encryptionKey;
}