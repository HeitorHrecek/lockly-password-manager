package com.example.lockly.application.usecases.passwords;

import com.example.lockly.dataproviderLayer.password.PasswordWithoutFolderDataProvider;
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
public class PasswordWithoutFolderService {

    private final PasswordWithoutFolderDataProvider dataProvider;
    private final UserService userService;
    private final CriptografiaService criptografiaService;

    public PasswordWithoutFolder register(PasswordWithoutFolder newPassword) {
        newPassword.setUser(userService.consultById(newPassword.getUser().getId()));
        SenhaEChave senhaEChave = criptografiaService.criptografar(newPassword.getContent());
        newPassword.setContent(senhaEChave.senhaCriptografada());
        newPassword.setEncryptionKey(senhaEChave.chave());
        return dataProvider.save(newPassword);
    }

    public List<PasswordWithoutFolder> consultAllByUser(Integer idUser) {
        List<PasswordWithoutFolder> result = dataProvider.consultAllByUser(idUser);
        if(result.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }
        return result;
    }

    public PasswordWithoutFolder queryByName(String name) {
        Optional<PasswordWithoutFolder> result = dataProvider.queryByName(name);
        if(result.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }
        return result.get();
    }

    public void delete(Integer id) {
        consultById(id);
        dataProvider.delete(id);
    }

    public PasswordWithoutFolder changeName(String newName, Integer id) {
        PasswordWithoutFolder existingPassword = consultById(id);
        existingPassword.setName(newName);
        return dataProvider.save(existingPassword);
    }

    public PasswordWithoutFolder consultById(Integer idPasswordWithoutFolder) {
        Optional<PasswordWithoutFolder> result = dataProvider.consultById(idPasswordWithoutFolder);
        if(result.isEmpty())
            throw new SenhaNaoEncontradaException();
        return result.get();
    }
}
