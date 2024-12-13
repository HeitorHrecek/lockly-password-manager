package com.example.lockly.application.usecases.passwords;

import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DescriptografiaSenhaService{

    private final PasswordWithoutFolderService passwordWithoutFolderService;
    private final SenhaComPastaService senhaComPastaService;
    private final CriptografiaService criptografiaService;

    public PasswordWithoutFolder descriptografiaSemPasta(Integer id) {
        PasswordWithoutFolder senha = passwordWithoutFolderService.consultById(id);
        senha.setContent(criptografiaService.descriptografar(senha.getContent(), senha.getEncryptionKey()));
        return senha;
    }

    public PasswordWithFolder descriptografiaComPasta(Integer id) {
        PasswordWithFolder senha = senhaComPastaService.consultById(id);
        senha.setContent(criptografiaService.descriptografar(senha.getContent(), senha.getEncryptionKey()));
        return senha;
    }
}