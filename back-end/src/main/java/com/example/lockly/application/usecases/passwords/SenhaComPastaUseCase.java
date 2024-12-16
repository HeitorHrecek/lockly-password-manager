package com.example.lockly.application.usecases.passwords;

import com.example.lockly.application.gateways.senha.SenhaComPastaGateway;
import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.application.usecases.PastaUseCase;
import com.example.lockly.application.usecases.UserService;
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
    private final UserService userService;
    private final PastaUseCase pastaUseCase;
    private final CriptografiaUseCase criptografiaUseCase;

    public PasswordWithFolder cadastrar(PasswordWithFolder novaSenha) {
        Optional<PasswordWithFolder> senhaExistente = gateway.consultarPorNome(novaSenha.getName());
        senhaExistente.ifPresent(password -> {
            throw new SenhaJaCadastradaException();
        });

        SenhaEChave senhaEChave = criptografiaUseCase.criptografar(novaSenha.getContent());
        novaSenha.setContent(senhaEChave.senhaCriptografada());
        novaSenha.setEncryptionKey(senhaEChave.chave());
        novaSenha.setUser(userService.consultById(novaSenha.getUser().getId()));
        novaSenha.setFolder(pastaUseCase.consultarPorId(novaSenha.getFolder().getId()));

        return gateway.salvar(novaSenha);
    }


    public List<PasswordWithFolder> listarPorUsuario(Integer idUsuario) {
        List<PasswordWithFolder> senhas = gateway.listarPorUsuario(idUsuario);
        if (senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return senhas;
    }

    public PasswordWithFolder consultarPorNome(String nome) {
        Optional<PasswordWithFolder> senha = gateway.consultarPorNome(nome);
        if (senha.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }
        return senha.get();
    }

    public void deletar(Integer id) {
        consultarPorId(id);
        gateway.deletar(id);
    }

    public PasswordWithFolder mudarNome(String nome, Integer idSenha) {
        PasswordWithFolder senha = consultarPorId(idSenha);
        senha.setName(nome);
        return gateway.salvar(senha);
    }

    public PasswordWithFolder consultarPorId(Integer id) {
        Optional<PasswordWithFolder> senha = gateway.consultarPorId(id);
        if (senha.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }
        return senha.get();
    }

    public List<PasswordWithFolder> listarPorPastas(Integer idPasta) {
        List<PasswordWithFolder> senhas = gateway.listarPorPasta(idPasta);
        if(senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return senhas;
    }
}
