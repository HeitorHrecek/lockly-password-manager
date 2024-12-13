package com.example.lockly.application.usecases.passwords;

import com.example.lockly.application.gateways.senha.SenhaComPastaGateway;
import com.example.lockly.domainLayer.passwords.PasswordWithFolder;
import com.example.lockly.application.usecases.FolderService;
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
public class SenhaComPastaService {

    private final SenhaComPastaGateway gateway;
    private final UserService userService;
    private final FolderService folderService;
    private final CriptografiaService criptografiaService;

    public PasswordWithFolder cadastrar(PasswordWithFolder novaSenha) {
        Optional<PasswordWithFolder> senhaExistente = gateway.consultarPorNome(novaSenha.getName());
        senhaExistente.ifPresent(password -> {
            throw new SenhaJaCadastradaException();
        });

        SenhaEChave senhaEChave = criptografiaService.criptografar(novaSenha.getContent());
        novaSenha.setContent(senhaEChave.senhaCriptografada());
        novaSenha.setEncryptionKey(senhaEChave.chave());
        novaSenha.setUser(userService.consultById(novaSenha.getUser().getId()));
        novaSenha.setFolder(folderService.findFolderById(novaSenha.getFolder().getId()));

        return gateway.salvar(novaSenha);
    }


    public List<PasswordWithFolder> listarPorUsuario(Integer idUsuario) {
        List<PasswordWithFolder> senhas = gateway.listarPorUsuario(idUsuario);
        if (senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return senhas;
    }

    public PasswordWithFolder queryByName(String name) {
        Optional<PasswordWithFolder> passwordWithFolder = gateway.consultarPorNome(name);
        if (passwordWithFolder.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }
        return passwordWithFolder.get();
    }

    public void deleteWithFolder(Integer id) {
        consultById(id);
        gateway.delete(id);
    }

    public PasswordWithFolder changeName(String name, Integer idPassword) {
        PasswordWithFolder existingPassword = consultById(idPassword);
        existingPassword.setName(name);
        return gateway.save(existingPassword);
    }

    public PasswordWithFolder consultById(Integer id) {
        Optional<PasswordWithFolder> resultQuery = gateway.consultById(id);
        if (resultQuery.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }
        return resultQuery.get();
    }

    public List<PasswordWithFolder> consultAllByFolder(Integer idFolder) {
        List<PasswordWithFolder> result = gateway.consultallByFolder(idFolder);
        if(result.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return result;
    }
}
