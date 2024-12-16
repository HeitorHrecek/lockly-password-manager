package com.example.lockly.application.gateways;

import com.example.lockly.domainLayer.Folder;

import java.util.List;
import java.util.Optional;

public interface PastaGateway {
    Optional<Folder> consultarPorId(Integer id);

    List<Folder> listarPorUsuario(Integer idUsuario);

    Folder salvar(Folder pasta);

    void deletar(Integer id);

    Optional<Folder> consultarPorNome(String nome);
}
