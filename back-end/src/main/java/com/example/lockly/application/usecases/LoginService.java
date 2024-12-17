package com.example.lockly.application.usecases;

import com.example.lockly.application.exceptions.usuario.EmailSenhaInvalidoException;
import com.example.lockly.application.usecases.passwords.SenhaUseCase;
import com.example.lockly.domain.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LoginService {

    private final UsuarioUseCase usuarioUseCase;
    private final SenhaUseCase senhaUseCase;

    public void login(String email, String password) {
        Usuario usuario = usuarioUseCase.consultarPorEmail(email);
        boolean correctPassword = senhaUseCase.validaSenha(password, usuario.getSenha());

        if (!correctPassword) {
            throw new EmailSenhaInvalidoException();
        }
    }
}
