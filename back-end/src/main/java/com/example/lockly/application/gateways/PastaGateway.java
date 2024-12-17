package com.example.lockly.application.gateways;

import com.example.lockly.domain.Pasta;

import java.util.List;
import java.util.Optional;

public interface PastaGateway {
    Optional<Pasta> consultarPorId(Integer id);

    List<Pasta> listarPorUsuario(Integer idUsuario);

    Pasta salvar(Pasta pasta);

    void deletar(Integer id);

    Optional<Pasta> consultarPorNome(String nome);
}
