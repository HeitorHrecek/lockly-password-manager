package com.example.lockly.serviceLayer.passwords;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PasswordService {

    private final PasswordEncoder passwordEncoder;

    public boolean validatePassword(String password, String passwordUser){
        return passwordEncoder.matches(password, passwordUser);
    }
}
