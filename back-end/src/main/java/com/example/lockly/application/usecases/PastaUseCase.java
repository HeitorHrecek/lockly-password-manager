package com.example.lockly.application.usecases;

import com.example.lockly.application.exceptions.pasta.NenhumaPastaEncontradaException;
import com.example.lockly.application.exceptions.pasta.PastaJaCadastradaException;
import com.example.lockly.application.exceptions.pasta.PastaNaoEncontradaException;
import com.example.lockly.application.gateways.PastaGateway;
import com.example.lockly.domain.Pasta;
import com.example.lockly.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Slf4j
public class PastaUseCase {

    private final PastaGateway gateway;
    private final UsuarioUseCase usuarioUseCase;

    public Pasta consultarPorId(Integer id) {
        log.info("Consultar pasta pelo seu id. id={}", id);
        Pasta pasta = gateway.consultarPorId(id)
                .orElseThrow(NenhumaPastaEncontradaException::new);
        log.info("Consulta realizada com sucesso. pasta={}", pasta);
        return pasta;
    }

    public Pasta cadastrar(Pasta novaPasta) {
        log.info("Cadastro de pasta. pasta={}", novaPasta);
        List<Pasta> pastasExistentes = gateway.listarPorUsuario(novaPasta.getUsuario().getId());

        boolean pastaExiste = pastasExistentes.stream()
                .anyMatch(folder -> folder.getNome().equalsIgnoreCase(novaPasta.getNome()));

        if (pastaExiste) {
            throw new PastaJaCadastradaException();
        }

        Usuario usuario = usuarioUseCase.consultarPorId(novaPasta.getUsuario().getId());
        novaPasta.setUsuario(usuario);
        Pasta pastaSalva = gateway.salvar(novaPasta);
        log.info("Pasta cadastrada com sucesso. pasta={}", pastaSalva);
        return pastaSalva;
    }

    public List<Pasta> listarPorUsuario(Integer userId) {
        log.info("Listar pastas pelo id do usuário. id={}", userId);
        List<Pasta> pastas = gateway.listarPorUsuario(userId);

        if (pastas.isEmpty()) {
            throw new NenhumaPastaEncontradaException();
        }

        log.info("Listagem realizada com sucesso. lista={}", pastas);

        return pastas;
    }

    public void deletar(Integer id) {
        log.info("Deleção de pasta pelo seu id. id={}", id);
        Pasta pasta = consultarPorId(id);
        gateway.deletar(id);
        log.info("Deleção feita com sucesso. pasta={}", pasta);
    }

    public Pasta mudarNome(String nome, Integer id) {
        log.info("Mudar nome da pasta. nome={} id={}", nome, id);
        Pasta pasta = consultarPorId(id);
        pasta.setNome(nome);
        Pasta pastaSalva = gateway.salvar(pasta);
        log.info("Mudança de nome feita com sucesso. pasta={}", pastaSalva);
        return pastaSalva;
    }

    public Pasta consultarPorNome(String nome) {
        log.info("Consulta pasta pelo seu nome. nome={}", nome);
        Optional<Pasta> pastaOptional = gateway.consultarPorNome(nome);

        if (pastaOptional.isEmpty()) {
            throw new PastaNaoEncontradaException();
        }

        Pasta pasta = pastaOptional.get();

        log.info("Consulta feita com sucesso. pasta={}", pasta);

        return pasta;
    }
}
