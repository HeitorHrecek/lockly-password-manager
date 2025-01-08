package com.example.lockly.application.usecases;

import com.example.lockly.application.exceptions.usuario.EmailSenhaInvalidoException;
import com.example.lockly.application.usecases.senhas.SenhaUseCase;
import com.example.lockly.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class LoginService {

    private final UsuarioUseCase usuarioUseCase;
    private final SenhaUseCase senhaUseCase;

    public void login(String email, String senha) {
        log.info("Login de usuário. email={} senha={}", email, senha);
        Usuario usuario = usuarioUseCase.consultarPorEmail(email);
        boolean correctPassword = senhaUseCase.validaSenha(senha, usuario.getSenha());

        if (!correctPassword) {
            throw new EmailSenhaInvalidoException();
        }

        log.info("Login realizado com sucesso.");
    }
}
