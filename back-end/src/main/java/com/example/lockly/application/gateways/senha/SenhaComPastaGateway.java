package com.example.lockly.application.gateways.senha;

import com.example.lockly.domainLayer.passwords.PasswordWithFolder;

import java.util.List;
import java.util.Optional;

public interface SenhaComPastaGateway {

    PasswordWithFolder salvar(PasswordWithFolder novaSenha);

    Optional<PasswordWithFolder> consultarPorNome(String name);

    List<PasswordWithFolder> listarPorUsuario(Integer idUsuario);

    void deletar(Integer id);

    Optional<PasswordWithFolder> consultarPorId(Integer id);

    List<PasswordWithFolder> listarPorPasta(Integer idFolder);
}
