package com.example.lockly.application.gateways.senha;

import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;

import java.util.List;
import java.util.Optional;

public interface SenhaSemPastaGateway {
    PasswordWithoutFolder salvar(PasswordWithoutFolder novaSenha);

    List<PasswordWithoutFolder> listarPorUsuario(Integer isUsuario);

    Optional<PasswordWithoutFolder> consultarPorNome(String nome);

    void deletar(Integer id);

    Optional<PasswordWithoutFolder> consultarPorId(Integer id);
}
