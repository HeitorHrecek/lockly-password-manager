package com.example.lockly.application.usecases.senhas;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class SenhaUseCase {

    private final PasswordEncoder passwordEncoder;

    public boolean validaSenha(String senha, String senhaUsuario) {
        log.info("Validar senha. senha={}", senha);
        log.info("senha do usuario={}", senhaUsuario);
        boolean senhaValida = passwordEncoder.matches(senha, senhaUsuario);
        log.info("Senha validada com sucesso. resultado={}", senhaValida);
        return senhaValida;
    }
}