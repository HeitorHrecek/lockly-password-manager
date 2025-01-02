package com.example.lockly.application.usecases.senhas;

import com.example.lockly.application.exceptions.senha.NenhumaSenhaEncontradaException;
import com.example.lockly.application.exceptions.senha.SenhaJaCadastradaException;
import com.example.lockly.application.exceptions.senha.SenhaNaoEncontradaException;
import com.example.lockly.application.gateways.senhas.SenhaComPastaGateway;
import com.example.lockly.application.usecases.PastaUseCase;
import com.example.lockly.application.usecases.UsuarioUseCase;
import com.example.lockly.domain.senhas.SenhaComPasta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SenhaComPastaUseCase {

    private final SenhaComPastaGateway gateway;
    private final UsuarioUseCase usuarioUseCase;
    private final PastaUseCase pastaUseCase;
    private final CriptografiaUseCase criptografiaUseCase;

    public SenhaComPasta cadastrar(SenhaComPasta novaSenha) {
        log.info("Cadastrando senha com pasta. senha={}", novaSenha);
        Optional<SenhaComPasta> senhaExistente = gateway.consultarPorNome(novaSenha.getNome());
        senhaExistente.ifPresent(password -> {
            throw new SenhaJaCadastradaException();
        });

        SenhaEChave senhaEChave = criptografiaUseCase.criptografar(novaSenha.getConteudo());
        novaSenha.setConteudo(senhaEChave.senhaCriptografada());
        novaSenha.setSenhaCriptografia(senhaEChave.chave());
        novaSenha.setUsuario(usuarioUseCase.consultarPorId(novaSenha.getUsuario().getId()));
        novaSenha.setPasta(pastaUseCase.consultarPorId(novaSenha.getPasta().getId()));

        SenhaComPasta senhaSalva = gateway.salvar(novaSenha);

        log.info("Senha cadastrada. senha={}", senhaSalva);

        return senhaSalva;
    }


    public List<SenhaComPasta> listarPorUsuario(Integer idUsuario) {
        log.info("Listar senhas com pasta por usuario. idUsuario={}", idUsuario);
        List<SenhaComPasta> senhas = gateway.listarPorUsuario(idUsuario);
        if (senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }

        log.info("Lista de senha com pasta. lista={}", senhas);

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
        if (senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return senhas;
    }
}
