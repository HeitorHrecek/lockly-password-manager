package com.example.lockly.infrastructure.dataprovider;

import com.example.lockly.application.gateways.PastaGateway;
import com.example.lockly.domain.Pasta;
import com.example.lockly.infrastructure.dataprovider.exceptions.pasta.*;
import com.example.lockly.infrastructure.mapper.PastaMapper;
import com.example.lockly.infrastructure.repository.PastaRepository;
import com.example.lockly.infrastructure.repository.entities.PastaEntity;
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

    private final PastaRepository repository;

    @Override
    public Pasta salvar(Pasta novaPasta) {
        PastaEntity pasta = PastaMapper.paraEntity(novaPasta);
        try {
            pasta = repository.save(pasta);
            return PastaMapper.paraDomain(pasta);
        } catch (Exception exception) {
            log.error("Erro ao salvar pasta.", exception);
            throw new ErroSalvarPastaException(exception.getMessage());
        }
    }

    @Override
    public List<Pasta> listarPorUsuario(Integer idUsuario) {
        try {
            List<PastaEntity> pastas = repository.listarPorUsuario(idUsuario);
            return pastas.stream().map(PastaMapper::paraDomain).collect(Collectors.toList());
        } catch (Exception exception) {
            log.error("Erro ao listar pastas por usuario.", exception);
            throw new ErroAoListarPastasPorUsuarioException(exception.getMessage());
        }
    }

    @Override
    public Optional<Pasta> consultarPorId(Integer id) {
        try {
            Optional<PastaEntity> pasta = repository.findById(id);
            return pasta.map(PastaMapper::paraDomain);
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
        Optional<PastaEntity> pasta;

        try {
            pasta = repository.findByNome(nome);
        } catch (Exception exception) {
            log.error("Erro ao consultar pasta por nome.", exception);
            throw new ErroConsultarPastaPorNomeException(exception.getMessage());
        }

        return pasta.map(PastaMapper::paraDomain);
    }
}

