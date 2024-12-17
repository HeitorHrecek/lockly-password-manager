package com.example.lockly.application.usecases.passwords;

import com.example.lockly.application.gateways.senha.SenhaComPastaGateway;
import com.example.lockly.domain.passwords.SenhaComPasta;
import com.example.lockly.application.usecases.PastaUseCase;
import com.example.lockly.application.usecases.UsuarioUseCase;
import com.example.lockly.application.exceptions.senha.NenhumaSenhaEncontradaException;
import com.example.lockly.application.exceptions.senha.SenhaJaCadastradaException;
import com.example.lockly.application.exceptions.senha.SenhaNaoEncontradaException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SenhaComPastaUseCase {

    private final SenhaComPastaGateway gateway;
    private final UsuarioUseCase usuarioUseCase;
    private final PastaUseCase pastaUseCase;
    private final CriptografiaUseCase criptografiaUseCase;

    public SenhaComPasta cadastrar(SenhaComPasta novaSenha) {
        Optional<SenhaComPasta> senhaExistente = gateway.consultarPorNome(novaSenha.getNome());
        senhaExistente.ifPresent(password -> {
            throw new SenhaJaCadastradaException();
        });

        SenhaEChave senhaEChave = criptografiaUseCase.criptografar(novaSenha.getConteudo());
        novaSenha.setConteudo(senhaEChave.senhaCriptografada());
        novaSenha.setSenhaCriptografia(senhaEChave.chave());
        novaSenha.setUsuario(usuarioUseCase.consultarPorId(novaSenha.getUsuario().getId()));
        novaSenha.setPasta(pastaUseCase.consultarPorId(novaSenha.getPasta().getId()));

        return gateway.salvar(novaSenha);
    }


    public List<SenhaComPasta> listarPorUsuario(Integer idUsuario) {
        List<SenhaComPasta> senhas = gateway.listarPorUsuario(idUsuario);
        if (senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return senhas;
    }

    public SenhaComPasta consultarPorNome(String nome) {
        Optional<SenhaComPasta> senha = gateway.consultarPorNome(nome);
        if (senha.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }
        return senha.get();
    }

    public void deletar(Integer id) {
        consultarPorId(id);
        gateway.deletar(id);
    }

    public SenhaComPasta mudarNome(String nome, Integer idSenha) {
        SenhaComPasta senha = consultarPorId(idSenha);
        senha.setNome(nome);
        return gateway.salvar(senha);
    }

    public SenhaComPasta consultarPorId(Integer id) {
        Optional<SenhaComPasta> senha = gateway.consultarPorId(id);
        if (senha.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }
        return senha.get();
    }

    public List<SenhaComPasta> listarPorPastas(Integer idPasta) {
        List<SenhaComPasta> senhas = gateway.listarPorPasta(idPasta);
        if(senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return senhas;
    }
}
