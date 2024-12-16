package com.example.lockly.application.usecases.passwords;

import com.example.lockly.application.gateways.senha.SenhaSemPastaGateway;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import com.example.lockly.application.usecases.UserService;
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
    private final UserService userService;
    private final CriptografiaUseCase criptografiaUseCase;

    public PasswordWithoutFolder cadastrar(PasswordWithoutFolder novaSenha) {
        novaSenha.setUser(userService.consultById(novaSenha.getUser().getId()));
        SenhaEChave senhaEChave = criptografiaUseCase.criptografar(novaSenha.getContent());
        novaSenha.setContent(senhaEChave.senhaCriptografada());
        novaSenha.setEncryptionKey(senhaEChave.chave());
        return gateway.salvar(novaSenha);
    }

    public List<PasswordWithoutFolder> listarPorUsuario(Integer isUsuario) {
        List<PasswordWithoutFolder> senhas = gateway.listarPorUsuario(isUsuario);
        if(senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return senhas;
    }

    public PasswordWithoutFolder consultarPorNome(String nome) {
        Optional<PasswordWithoutFolder> senha = gateway.consultarPorNome(nome);
        if(senha.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }
        return senha.get();
    }

    public void deletar(Integer id) {
        consultarPorId(id);
        gateway.deletar(id);
    }

    public PasswordWithoutFolder mudarNome(String novoNome, Integer id) {
        PasswordWithoutFolder senha = consultarPorId(id);
        senha.setName(novoNome);
        return gateway.salvar(senha);
    }

    public PasswordWithoutFolder consultarPorId(Integer id) {
        Optional<PasswordWithoutFolder> senha = gateway.consultarPorId(id);
        if(senha.isEmpty())
            throw new SenhaNaoEncontradaException();
        return senha.get();
    }
}
