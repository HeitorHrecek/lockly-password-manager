package com.example.lockly.application.usecases.passwords;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SenhaService {

    private final PasswordEncoder passwordEncoder;

    public boolean validaSenha(String senha, String senhaUsuario){
        return passwordEncoder.matches(senha, senhaUsuario);
    }
}