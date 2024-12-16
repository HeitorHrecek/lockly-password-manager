package com.example.lockly.application.usecases;
import com.example.lockly.application.gateways.PastaGateway;
import com.example.lockly.entrypoint.dto.PastaDto;
import com.example.lockly.domainLayer.Folder;
import com.example.lockly.domainLayer.User;
import com.example.lockly.mapper.FolderMapper;
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
    private final UserService userService;

    public Folder consultarPorId(Integer id) {
        return gateway.consultarPorId(id)
                .orElseThrow(NenhumaPastaEncontradaException::new);
    }

    public PastaDto cadastrar(PastaDto pastaDto) {
        List<Folder> pastasExistentes = gateway.listarPorUsuario(pastaDto.usuarioDto().id());

        boolean pastaExiste = pastasExistentes.stream()
                .anyMatch(folder -> folder.getName().equalsIgnoreCase(pastaDto.nome()));

        if (pastaExiste) {
            throw new PastaJaCadastradaException();
        }

        Folder pasta = FolderMapper.forDomainFromDto(pastaDto);
        User usuario = userService.consultById(pastaDto.usuarioDto().id());
        pasta.setUser(usuario);
        return FolderMapper.forDto(gateway.salvar(pasta));
    }

    public List<PastaDto> listarPorUsuario(Integer userId) {
        List<Folder> senhas = gateway.listarPorUsuario(userId);

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
        Folder pasta = consultarPorId(id);
        pasta.setName(nome);
        return FolderMapper.forDto(gateway.salvar(pasta));
    }

    public PastaDto consultarPorNome(String nome) {
        Optional<Folder> pasta = gateway.consultarPorNome(nome);

        if(pasta.isEmpty()) {
            throw new PastaNaoEncontradaException();
        }

        return FolderMapper.forDto(pasta.get());
    }
}
