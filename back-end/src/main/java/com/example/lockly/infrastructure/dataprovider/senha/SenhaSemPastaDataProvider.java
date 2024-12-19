package com.example.lockly.infrastructure.dataprovider.senha;

import com.example.lockly.application.gateways.senha.SenhaSemPastaGateway;
import com.example.lockly.domain.passwords.SenhaSemPasta;
import com.example.lockly.infrastructure.dataprovider.exceptions.senha.*;
import com.example.lockly.infrastructure.mapper.passwords.PasswordWithoutFolderMapper;
import com.example.lockly.infrastructure.repositoryLayer.entities.passwords.PasswordWithoutFolderEntity;
import com.example.lockly.infrastructure.repositoryLayer.password.PasswordWithoutFolderRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class SenhaSemPastaDataProvider implements SenhaSemPastaGateway {

    private final PasswordWithoutFolderRepository repository;

    @Override
    public SenhaSemPasta salvar(SenhaSemPasta novaSenha) {
        PasswordWithoutFolderEntity senha = PasswordWithoutFolderMapper.forEnttiy(novaSenha);
        try {
            senha = repository.save(senha);
        } catch (Exception exception) {
            log.error("Erro ao salvar senha sem pasta.", exception);
            throw new ErroSalvarSenhaException(exception.getMessage());
        }
        return PasswordWithoutFolderMapper.forDomain(senha);
    }

    @Override
    public List<SenhaSemPasta> listarPorUsuario(Integer idUsuario) {
        List<PasswordWithoutFolderEntity> senhas;
        try {
            senhas = repository.consultAllByUser(idUsuario);
        } catch (Exception exception) {
            log.error("Erro ao listar senhas com pasta por usuario.", exception);
            throw new ErroListarSenhasPorUsuarioException(exception.getMessage());
        }
        return senhas.stream().map(PasswordWithoutFolderMapper::forDomain).toList();
    }

    @Override
    public Optional<SenhaSemPasta> consultarPorNome(String nome) {
        Optional<PasswordWithoutFolderEntity> senha;
        try {
            senha = repository.findByName(nome);
        }catch (Exception exception) {
            log.error("Erro ao consultar senha sem pasta por nome.", exception);
            throw new ErroAoConsultarPastaPorNomeException(exception.getMessage());
        }
        return senha.map(PasswordWithoutFolderMapper::forDomain);
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
        Optional<PasswordWithoutFolderEntity> senha;
        try {
            senha = repository.findById(id);
        }catch (Exception exception) {
            log.error("Erro ao consultar senha com pasta por id.");
            throw new ErroConsultarSenhaPorIdException(exception.getMessage());
        }
        return senha.map(PasswordWithoutFolderMapper::forDomain);
    }
}
