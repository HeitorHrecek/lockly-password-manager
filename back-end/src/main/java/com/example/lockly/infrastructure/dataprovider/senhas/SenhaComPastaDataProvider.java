package com.example.lockly.infrastructure.dataprovider.senhas;

import com.example.lockly.application.gateways.senhas.SenhaComPastaGateway;
import com.example.lockly.domain.senhas.SenhaComPasta;
import com.example.lockly.infrastructure.dataprovider.exceptions.senha.*;
import com.example.lockly.infrastructure.mapper.senhas.SenhaComPastaMapper;
import com.example.lockly.infrastructure.repository.entities.senha.SenhaComPastaEntity;
import com.example.lockly.infrastructure.repository.senhas.SenhaComPastaRepositoy;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class SenhaComPastaDataProvider implements SenhaComPastaGateway {

    private final SenhaComPastaRepositoy repository;

    @Override
    public SenhaComPasta salvar(SenhaComPasta newPassword) {
        SenhaComPastaEntity senha = SenhaComPastaMapper.paraEntity(newPassword);

        try {
            senha = repository.save(senha);
        } catch (Exception exception) {
            log.error("Erro ao salvar senha com pasta.", exception);
            throw new ErroSalvarSenhaException(exception.getMessage());
        }

        return SenhaComPastaMapper.paraDomain(senha);
    }

    @Override
    public List<SenhaComPasta> listarPorUsuario(Integer idUser) {
        List<SenhaComPastaEntity> senhas;
        try {
            senhas = repository.consultarPorUsuario(idUser);
        } catch (Exception exception) {
            log.error("Erro ao listar senhas com pasta por usuario", exception);
            throw new ErroListarSenhasPorUsuarioException(exception.getMessage());
        }
        return SenhaComPastaMapper.paraDomains(senhas);
    }

    @Override
    public Optional<SenhaComPasta> consultarPorNome(String name) {
        Optional<SenhaComPastaEntity> senha;
        try {
            senha = repository.consultarPorNome(name);
        } catch (Exception exception) {
            log.error("Erro ao buscar senha com pasta por nome", exception);
            throw new ErroAoConsultarPastaPorNomeException(exception.getMessage());
        }
        return senha.map(SenhaComPastaMapper::paraDomain);
    }

    @Override
    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            log.error("Erro ao deletar senha com pasta.", exception);
            throw new ErroDeletarSenhaException(exception.getMessage());
        }
    }

    @Override
    public Optional<SenhaComPasta> consultarPorId(Integer id) {
        Optional<SenhaComPastaEntity> senha;
        try {
            senha = repository.findById(id);
        } catch (Exception exception) {
            log.error("Erro ao consultar senha com pasta por id.", exception);
            throw new ErroConsultarSenhaPorIdException(exception.getMessage());
        }

        return senha.map(SenhaComPastaMapper::paraDomain);
    }

    @Override
    public List<SenhaComPasta> listarPorPasta(Integer idFolder) {
        List<SenhaComPastaEntity> senhas;
        try {
            senhas = repository.consultarPorPasta(idFolder);
        } catch (Exception exception) {
            log.error("Erro ao listar senhas com pasta por pasta.");
            throw new ErroAoListarSenhasPorPastaException(exception.getMessage());
        }
        return SenhaComPastaMapper.paraDomains(senhas);
    }
}
