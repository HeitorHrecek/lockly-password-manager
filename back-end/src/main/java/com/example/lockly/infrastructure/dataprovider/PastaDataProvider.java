package com.example.lockly.infrastructure.dataprovider;

import com.example.lockly.application.gateways.PastaGateway;
import com.example.lockly.domain.Pasta;
import com.example.lockly.infrastructure.dataprovider.exceptions.pasta.*;
import com.example.lockly.infrastructure.mapper.FolderMapper;
import com.example.lockly.infrastructure.repositoryLayer.FolderRepository;
import com.example.lockly.infrastructure.repositoryLayer.entities.FolderEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Component
public class PastaDataProvider implements PastaGateway {

    private final FolderRepository repository;

    @Override
    public Pasta salvar(Pasta novaPasta) {
        FolderEntity pasta = FolderMapper.forEntity(novaPasta);
        try {
            pasta = repository.save(pasta);
            return FolderMapper.forDomain(pasta);
        } catch (Exception exception) {
            log.error("Erro ao salvar pasta.", exception);
            throw new ErroSalvarPastaException(exception.getMessage());
        }
    }

    @Override
    public List<Pasta> listarPorUsuario(Integer idUsuario) {
        try {
            List<FolderEntity> pastas = repository.consultAllByUser(idUsuario);
            return pastas.stream()
                    .map(FolderMapper::forDomain)
                    .collect(Collectors.toList());
        } catch (Exception exception) {
            log.error("Erro ao listar pastas por usuario.", exception);
            throw new ErroAoListarPastasPorUsuarioException(exception.getMessage());
        }
    }

    @Override
    public Optional<Pasta> consultarPorId(Integer id) {
        try {
            Optional<FolderEntity> pasta = repository.findById(id);
            return pasta.map(FolderMapper::forDomain);
        } catch (Exception exception) {
            log.error("Erro ao consultar pasta por id.", exception);
            throw new ErroConsultarPastaPorIdException(exception.getMessage());
        }
    }

    @Override
    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            log.error("Erro ao deletar pasta." + id, exception);
            throw new ErroDeletarPastaException(exception.getMessage());
        }
    }

    @Override
    public Optional<Pasta> consultarPorNome(String nome) {
        Optional<FolderEntity> pasta;

        try {
            pasta = repository.findByName(nome);
        } catch (Exception exception) {
            log.error("Erro ao consultar pasta por nome.", exception);
            throw new ErroConsultarPastaPorNomeException(exception.getMessage());
        }

        return pasta.map(FolderMapper::forDomain);
    }
}

