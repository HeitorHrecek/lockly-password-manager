package com.example.lockly.application.usecases;

import com.example.lockly.application.exceptions.usuario.UsuarioJaCadastradoException;
import com.example.lockly.application.exceptions.usuario.UsuarioNaoEncontradoException;
import com.example.lockly.application.gateways.UsuarioGateway;
import com.example.lockly.application.usecases.senhas.CriptografiaUseCase;
import com.example.lockly.domain.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioUseCase {
    private final UsuarioGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;

    public Usuario cadastrar(Usuario novoUsuario) {
        Optional<Usuario> usuarioExistente = gateway.consultarPorEmail(novoUsuario.getEmail());
        usuarioExistente.ifPresent(user -> {
            throw new UsuarioJaCadastradoException();
        });

        novoUsuario.setSenha(criptografiaUseCase.criptografiaLogin(novoUsuario.getSenha()));

        return gateway.salvar(novoUsuario);
    }

    public Usuario consultarPorId(Integer id) {
        Optional<Usuario> usuario = gateway.consultarPorId(id);
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }

        return usuario.get();
    }

    public Usuario consultarPorEmail(String email) {
        Optional<Usuario> usuario = gateway.consultarPorEmail(email);
        if (usuario.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }

        return usuario.get();
    }

    public void deletar(Integer id) {
        consultarPorId(id);
        gateway.deletar(id);
    }

    public Usuario alterar(Usuario alteredUsuario, Integer id) {
        Usuario usuario = consultarPorId(id);
        usuario.setData(alteredUsuario);
        return gateway.salvar(usuario);
    }


}
