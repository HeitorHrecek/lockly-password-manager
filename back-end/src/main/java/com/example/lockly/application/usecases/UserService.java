package com.example.lockly.application.usecases;

import com.example.lockly.dataproviderLayer.UserDataProvider;
import com.example.lockly.domainLayer.User;
import com.example.lockly.application.exceptions.usuario.EmailSenhaInvalidoException;
import com.example.lockly.application.exceptions.usuario.UsuarioNaoEncontradoException;
import com.example.lockly.application.exceptions.usuario.UsuarioJaCadastradoException;
import com.example.lockly.application.usecases.passwords.CriptografiaService;
import com.example.lockly.application.usecases.passwords.SenhaService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserDataProvider dataProvider;
    private final SenhaService senhaService;
    private final CriptografiaService criptografiaService;

    public User register(User newUser){
        Optional<User> resultConsult = dataProvider.searchByEmail(newUser.getEmail());
        resultConsult.ifPresent(user -> {
            throw new UsuarioJaCadastradoException();
        });

        newUser.setPassword(criptografiaService.criptografiaLogin(newUser.getPassword()));

        return dataProvider.save(newUser);
    }

    public User consultById(Integer id){
        Optional<User> userConsult = dataProvider.searchById(id);
        if (userConsult.isEmpty()){
            throw new UsuarioNaoEncontradoException();
        }

        return userConsult.get();
    }

    public User consultByEmail(String email){
        Optional<User> userConsult = dataProvider.searchByEmail(email);
        if (userConsult.isEmpty()){
            throw new UsuarioNaoEncontradoException();
        }

        return userConsult.get();
    }

    public void delete(Integer id){
        consultById(id);
        dataProvider.delete(id);
    }

    public User change(User alteredUser, Integer id){
        User user = consultById(id);

        user.setData(alteredUser);
        return dataProvider.save(user);
    }

    public void login (String email, String password){
        User user = consultByEmail(email);
        boolean correctPassword = senhaService.validaSenha(password, user.getPassword());

        if (!correctPassword){
            throw new EmailSenhaInvalidoException();
        }
    }
}
