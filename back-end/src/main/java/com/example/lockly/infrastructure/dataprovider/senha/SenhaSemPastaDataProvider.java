package com.example.lockly.infrastructure.dataprovider.senha;

import com.example.lockly.application.gateways.senha.SenhaSemPastaGateway;
import com.example.lockly.domain.passwords.SenhaSemPasta;
import com.example.lockly.infrastructure.dataprovider.exceptions.senha.*;
import com.example.lockly.infrastructure.mapper.passwords.SenhaSemPastaMapper;
import com.example.lockly.infrastructure.repository.entities.senha.SenhaSemPastaEntity;
import com.example.lockly.infrastructure.repository.senha.SenhaSemPastaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class SenhaSemPastaDataProvider implements SenhaSemPastaGateway {

    private final SenhaSemPastaRepository repository;

    @Override
    public SenhaSemPasta salvar(SenhaSemPasta novaSenha) {
        SenhaSemPastaEntity senha = SenhaSemPastaMapper.paraEntity(novaSenha);
        try {
            senha = repository.save(senha);
        } catch (Exception exception) {
            log.error("Erro ao salvar senha sem pasta.", exception);
            throw new ErroSalvarSenhaException(exception.getMessage());
        }
        return SenhaSemPastaMapper.paraDomain(senha);
    }

    @Override
    public List<SenhaSemPasta> listarPorUsuario(Integer idUsuario) {
        List<SenhaSemPastaEntity> senhas;
        try {
            senhas = repository.listarPorUsuario(idUsuario);
        } catch (Exception exception) {
            log.error("Erro ao listar senhas com pasta por usuario.", exception);
            throw new ErroListarSenhasPorUsuarioException(exception.getMessage());
        }
        return senhas.stream().map(SenhaSemPastaMapper::paraDomain).toList();
    }

    @Override
    public Optional<SenhaSemPasta> consultarPorNome(String nome) {
        Optional<SenhaSemPastaEntity> senha;
        try {
            senha = repository.findByNome(nome);
        }catch (Exception exception) {
            log.error("Erro ao consultar senha sem pasta por nome.", exception);
            throw new ErroAoConsultarPastaPorNomeException(exception.getMessage());
        }
        return senha.map(SenhaSemPastaMapper::paraDomain);
    }

    @Override
    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        }catch (Exception exception) {
            log.error("Erro ao deletar senha com pasta.", exception);
            throw new ErroDeletarSenhaException(exception.getMessage());
        }
    }

    @Override
    public Optional<SenhaSemPasta> consultarPorId(Integer id) {
        Optional<SenhaSemPastaEntity> senha;
        try {
            senha = repository.findById(id);
        }catch (Exception exception) {
            log.error("Erro ao consultar senha com pasta por id.");
            throw new ErroConsultarSenhaPorIdException(exception.getMessage());
        }
        return senha.map(SenhaSemPastaMapper::paraDomain);
    }
}
