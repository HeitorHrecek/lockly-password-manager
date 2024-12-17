package com.example.lockly.infrastructure.dataprovider.password;

import com.example.lockly.application.gateways.senha.SenhaComPastaGateway;
import com.example.lockly.domain.passwords.SenhaComPasta;
import com.example.lockly.infrastructure.dataprovider.exceptions.senha.*;
import com.example.lockly.infrastructure.mapper.passwords.PasswordWithFolderMapper;
import com.example.lockly.infrastructure.repositoryLayer.entities.passwords.PasswordWithFolderEntity;
import com.example.lockly.infrastructure.repositoryLayer.password.PasswordWithFolderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class SenhaComPastaDataProvider implements SenhaComPastaGateway {

    private final PasswordWithFolderRepository repository;

    @Override
    public SenhaComPasta salvar(SenhaComPasta newPassword) {
        PasswordWithFolderEntity senha = PasswordWithFolderMapper.forEntity(newPassword);

        try {
            senha = repository.save(senha);
        } catch (Exception exception) {
            log.error("Erro ao salvar senha com pasta.", exception);
            throw new ErroSalvarSenhaException(exception.getMessage());
        }

        return PasswordWithFolderMapper.forDomain(senha);
    }

    @Override
    public List<SenhaComPasta> listarPorUsuario(Integer idUser) {
        List<PasswordWithFolderEntity> senhas;
        try {
            senhas = repository.findByUser(idUser);
        } catch (Exception exception) {
            log.error("Erro ao listar senhas com pasta por usuario", exception);
            throw new ErroListarSenhasPorUsuarioException(exception.getMessage());
        }
        return PasswordWithFolderMapper.forDomains(senhas);
    }

    @Override
    public Optional<SenhaComPasta> consultarPorNome(String name) {
        Optional<PasswordWithFolderEntity> senha;
        try {
            senha = repository.findByName(name);
        } catch (Exception exception) {
            log.error("Erro ao buscar senha com pasta por nome", exception);
            throw new ErroAoConsultarPastaPorNomeException(exception.getMessage());
        }
        return senha.map(PasswordWithFolderMapper::forDomain);
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
        Optional<PasswordWithFolderEntity> senha;
        try {
            senha = repository.findById(id);
        } catch (Exception exception) {
            log.error("Erro ao consultar senha com pasta por id.", exception);
            throw new ErroConsultarSenhaPorIdException(exception.getMessage());
        }

        return senha.map(PasswordWithFolderMapper::forDomain);
    }

    @Override
    public List<SenhaComPasta> listarPorPasta(Integer idFolder) {
        List<PasswordWithFolderEntity> senhas;
        try {
            senhas = repository.findByFolder(idFolder);
        } catch (Exception exception) {
            log.error("Erro ao listar senhas com pasta por pasta.");
            throw new ErroAoListarSenhasPorPastaException(exception.getMessage());
        }
        return PasswordWithFolderMapper.forDomains(senhas);
    }
}
