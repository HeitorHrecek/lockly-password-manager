package com.example.lockly.application.gateways;

import com.example.lockly.domain.Usuario;

import java.util.Optional;

public interface UsuarioGateway {
    Optional<Usuario> consultarPorEmail(String email);

    Usuario salvar(Usuario novoUsuario);

    Optional<Usuario> consultarPorId(Integer id);

    void deletar(Integer id);
}
