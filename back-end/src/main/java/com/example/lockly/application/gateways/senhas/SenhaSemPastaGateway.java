package com.example.lockly.application.gateways.senhas;

import com.example.lockly.domain.senhas.SenhaSemPasta;

import java.util.List;
import java.util.Optional;

public interface SenhaSemPastaGateway {
    SenhaSemPasta salvar(SenhaSemPasta novaSenha);

    List<SenhaSemPasta> listarPorUsuario(Integer isUsuario);

    Optional<SenhaSemPasta> consultarPorNome(String nome);

    void deletar(Integer id);

    Optional<SenhaSemPasta> consultarPorId(Integer id);
}
