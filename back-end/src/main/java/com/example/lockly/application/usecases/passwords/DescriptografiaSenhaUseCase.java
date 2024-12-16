package com.example.lockly.application.usecases.passwords;

import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DescriptografiaSenhaUseCase {

    private final SenhaSemPastaUseCase passwordWithoutFolderService;
    private final SenhaComPastaUseCase senhaComPastaUseCase;
    private final CriptografiaUseCase criptografiaUseCase;

    public PasswordWithoutFolder descriptografiaSemPasta(Integer id) {
        PasswordWithoutFolder senha = passwordWithoutFolderService.consultarPorId(id);
        senha.setContent(criptografiaUseCase.descriptografar(senha.getContent(), senha.getEncryptionKey()));
        return senha;
    }

    public PasswordWithFolder descriptografiaComPasta(Integer id) {
        PasswordWithFolder senha = senhaComPastaUseCase.consultarPorId(id);
        senha.setContent(criptografiaUseCase.descriptografar(senha.getContent(), senha.getEncryptionKey()));
        return senha;
    }
}