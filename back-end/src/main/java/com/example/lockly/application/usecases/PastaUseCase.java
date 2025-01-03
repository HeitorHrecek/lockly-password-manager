package com.example.lockly.application.usecases;

import com.example.lockly.application.exceptions.pasta.NenhumaPastaEncontradaException;
import com.example.lockly.application.exceptions.pasta.PastaJaCadastradaException;
import com.example.lockly.application.exceptions.pasta.PastaNaoEncontradaException;
import com.example.lockly.application.gateways.PastaGateway;
import com.example.lockly.domain.Pasta;
import com.example.lockly.domain.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PastaUseCase {

    private final PastaGateway gateway;
    private final UsuarioUseCase usuarioUseCase;

    public Pasta consultarPorId(Integer id) {
        return gateway.consultarPorId(id)
                .orElseThrow(NenhumaPastaEncontradaException::new);
    }

    public Pasta cadastrar(Pasta novaPasta) {
        List<Pasta> pastasExistentes = gateway.listarPorUsuario(novaPasta.getUsuario().getId());

        boolean pastaExiste = pastasExistentes.stream()
                .anyMatch(folder -> folder.getNome().equalsIgnoreCase(novaPasta.getNome()));

        if (pastaExiste) {
            throw new PastaJaCadastradaException();
        }

        Usuario usuario = usuarioUseCase.consultarPorId(novaPasta.getUsuario().getId());
        novaPasta.setUsuario(usuario);
        return gateway.salvar(novaPasta);
    }

    public List<Pasta> listarPorUsuario(Integer userId) {
        List<Pasta> senhas = gateway.listarPorUsuario(userId);

        if (senhas.isEmpty()) {
            throw new NenhumaPastaEncontradaException();
        }

        return senhas;
    }

    public void deletar(Integer id) {
        consultarPorId(id);
        gateway.deletar(id);
    }

    public Pasta mudarNome(String nome, Integer id) {
        Pasta pasta = consultarPorId(id);
        pasta.setNome(nome);
        return gateway.salvar(pasta);
    }

    public Pasta consultarPorNome(String nome) {
        Optional<Pasta> pasta = gateway.consultarPorNome(nome);

        if (pasta.isEmpty()) {
            throw new PastaNaoEncontradaException();
        }

        return pasta.get();
    }
}
