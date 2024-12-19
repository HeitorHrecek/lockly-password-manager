package com.example.lockly.application.usecases.senha;

import com.example.lockly.application.gateways.senha.SenhaSemPastaGateway;
import com.example.lockly.domain.passwords.SenhaSemPasta;
import com.example.lockly.application.usecases.UsuarioUseCase;
import com.example.lockly.application.exceptions.senha.NenhumaSenhaEncontradaException;
import com.example.lockly.application.exceptions.senha.SenhaNaoEncontradaException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SenhaSemPastaUseCase {

    private final SenhaSemPastaGateway gateway;
    private final UsuarioUseCase usuarioUseCase;
    private final CriptografiaUseCase criptografiaUseCase;

    public SenhaSemPasta cadastrar(SenhaSemPasta novaSenha) {
        novaSenha.setUsuario(usuarioUseCase.consultarPorId(novaSenha.getUsuario().getId()));
        SenhaEChave senhaEChave = criptografiaUseCase.criptografar(novaSenha.getConteudo());
        novaSenha.setConteudo(senhaEChave.senhaCriptografada());
        novaSenha.setChaveCriptografia(senhaEChave.chave());
        return gateway.salvar(novaSenha);
    }

    public List<SenhaSemPasta> listarPorUsuario(Integer isUsuario) {
        List<SenhaSemPasta> senhas = gateway.listarPorUsuario(isUsuario);
        if(senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return senhas;
    }

    public SenhaSemPasta consultarPorNome(String nome) {
        Optional<SenhaSemPasta> senha = gateway.consultarPorNome(nome);
        if(senha.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }
        return senha.get();
    }

    public void deletar(Integer id) {
        consultarPorId(id);
        gateway.deletar(id);
    }

    public SenhaSemPasta mudarNome(String novoNome, Integer id) {
        SenhaSemPasta senha = consultarPorId(id);
        senha.setNome(novoNome);
        return gateway.salvar(senha);
    }

    public SenhaSemPasta consultarPorId(Integer id) {
        Optional<SenhaSemPasta> senha = gateway.consultarPorId(id);
        if(senha.isEmpty())
            throw new SenhaNaoEncontradaException();
        return senha.get();
    }
}
