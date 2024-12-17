package com.example.lockly.application.usecases;
import com.example.lockly.application.gateways.PastaGateway;
import com.example.lockly.domain.Usuario;
import com.example.lockly.entrypoint.dto.PastaDto;
import com.example.lockly.domain.Pasta;
import com.example.lockly.infrastructure.mapper.FolderMapper;
import com.example.lockly.application.exceptions.pasta.PastaNaoEncontradaException;
import com.example.lockly.application.exceptions.pasta.PastaJaCadastradaException;
import com.example.lockly.application.exceptions.pasta.NenhumaPastaEncontradaException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class PastaUseCase {

    private final PastaGateway gateway;
    private final UsuarioUseCase usuarioUseCase;

    public Pasta consultarPorId(Integer id) {
        return gateway.consultarPorId(id)
                .orElseThrow(NenhumaPastaEncontradaException::new);
    }

    public PastaDto cadastrar(PastaDto pastaDto) {
        List<Pasta> pastasExistentes = gateway.listarPorUsuario(pastaDto.usuarioDto().id());

        boolean pastaExiste = pastasExistentes.stream()
                .anyMatch(folder -> folder.getNome().equalsIgnoreCase(pastaDto.nome()));

        if (pastaExiste) {
            throw new PastaJaCadastradaException();
        }

        Pasta pasta = FolderMapper.forDomainFromDto(pastaDto);
        Usuario usuario = usuarioUseCase.consultarPorId(pastaDto.usuarioDto().id());
        pasta.setUsuario(usuario);
        return FolderMapper.forDto(gateway.salvar(pasta));
    }

    public List<PastaDto> listarPorUsuario(Integer userId) {
        List<Pasta> senhas = gateway.listarPorUsuario(userId);

        if (senhas.isEmpty()) {
            throw new NenhumaPastaEncontradaException();
        }

        return senhas.stream()
                .map(FolderMapper::forDto)
                .collect(Collectors.toList());
    }

    public void deletar(Integer id) {
        consultarPorId(id);
        gateway.deletar(id);
    }

    public PastaDto mudarNome(String nome, Integer id) {
        Pasta pasta = consultarPorId(id);
        pasta.setNome(nome);
        return FolderMapper.forDto(gateway.salvar(pasta));
    }

    public PastaDto consultarPorNome(String nome) {
        Optional<Pasta> pasta = gateway.consultarPorNome(nome);

        if(pasta.isEmpty()) {
            throw new PastaNaoEncontradaException();
        }

        return FolderMapper.forDto(pasta.get());
    }
}
