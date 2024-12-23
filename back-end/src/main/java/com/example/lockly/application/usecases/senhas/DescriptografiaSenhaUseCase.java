package com.example.lockly.application.usecases.senhas;

import com.example.lockly.domain.senhas.SenhaComPasta;
import com.example.lockly.domain.senhas.SenhaSemPasta;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DescriptografiaSenhaUseCase {

    private final SenhaSemPastaUseCase senhaSemPastaUseCase;
    private final SenhaComPastaUseCase senhaComPastaUseCase;
    private final CriptografiaUseCase criptografiaUseCase;

    public SenhaSemPasta descriptografiaSemPasta(Integer id) {
        SenhaSemPasta senha = senhaSemPastaUseCase.consultarPorId(id);
        senha.setConteudo(criptografiaUseCase.descriptografar(senha.getConteudo(), senha.getChaveCriptografia()));
        return senha;
    }

    public SenhaComPasta descriptografiaComPasta(Integer id) {
        SenhaComPasta senha = senhaComPastaUseCase.consultarPorId(id);
        senha.setConteudo(criptografiaUseCase.descriptografar(senha.getConteudo(), senha.getSenhaCriptografia()));
        return senha;
    }
}