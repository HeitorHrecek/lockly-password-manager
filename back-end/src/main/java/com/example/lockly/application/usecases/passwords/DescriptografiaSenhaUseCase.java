package com.example.lockly.application.usecases.passwords;

import com.example.lockly.domain.passwords.SenhaComPasta;
import com.example.lockly.domain.passwords.SenhaSemPasta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DescriptografiaSenhaUseCase {

    private final SenhaSemPastaUseCase passwordWithoutFolderService;
    private final SenhaComPastaUseCase senhaComPastaUseCase;
    private final CriptografiaUseCase criptografiaUseCase;

    public SenhaSemPasta descriptografiaSemPasta(Integer id) {
        SenhaSemPasta senha = passwordWithoutFolderService.consultarPorId(id);
        senha.setConteudo(criptografiaUseCase.descriptografar(senha.getConteudo(), senha.getChaveCriptografia()));
        return senha;
    }

    public SenhaComPasta descriptografiaComPasta(Integer id) {
        SenhaComPasta senha = senhaComPastaUseCase.consultarPorId(id);
        senha.setConteudo(criptografiaUseCase.descriptografar(senha.getConteudo(), senha.getSenhaCriptografia()));
        return senha;
    }
}