package com.example.lockly.application.gateways.senhas;

import com.example.lockly.domain.senhas.SenhaComPasta;

import java.util.List;
import java.util.Optional;

public interface SenhaComPastaGateway {

    SenhaComPasta salvar(SenhaComPasta novaSenha);

    Optional<SenhaComPasta> consultarPorNome(String name);

    List<SenhaComPasta> listarPorUsuario(Integer idUsuario);

    void deletar(Integer id);

    Optional<SenhaComPasta> consultarPorId(Integer id);

    List<SenhaComPasta> listarPorPasta(Integer idFolder);
}
