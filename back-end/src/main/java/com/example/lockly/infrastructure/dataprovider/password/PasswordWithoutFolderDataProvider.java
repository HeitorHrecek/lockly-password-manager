package com.example.lockly.infrastructure.dataprovider.password;

import com.example.lockly.dataproviderLayer.exceptions.password.*;
import com.example.lockly.domain.passwords.SenhaSemPasta;
import com.example.lockly.infrastructure.dataprovider.exceptions.senha.*;
import com.example.lockly.infrastructure.mapper.passwords.PasswordWithoutFolderMapper;
import com.example.lockly.infrastructure.repositoryLayer.password.PasswordWithoutFolderRepository;
import com.example.lockly.infrastructure.repositoryLayer.entities.passwords.PasswordWithoutFolderEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
@Slf4j
public class PasswordWithoutFolderDataProvider {

    private final PasswordWithoutFolderRepository repository;

    public SenhaSemPasta save(SenhaSemPasta newPassword) {
        PasswordWithoutFolderEntity password = PasswordWithoutFolderMapper.forEnttiy(newPassword);
        try {
            password = repository.save(password);
        } catch (Exception exception) {
            log.error("Error saving password without folder", exception);
            throw new ErroSalvarSenhaException(exception.getMessage());
        }
        return PasswordWithoutFolderMapper.forDomain(password);
    }

    public List<SenhaSemPasta> consultAllByUser(Integer idUser) {
        List<PasswordWithoutFolderEntity> passwordList;
        try {
            passwordList = repository.consultAllByUser(idUser);
        } catch (Exception exception) {
            log.error("Error when querying all passwords without folders by user", exception);
            throw new ErroListarSenhasPorUsuarioException(exception.getMessage());
        }
        return passwordList.stream().map(PasswordWithoutFolderMapper::forDomain).toList();
    }

    public Optional<SenhaSemPasta> queryByName(String name) {
        Optional<PasswordWithoutFolderEntity> password;
        try {
            password = repository.findByName(name);
        }catch (Exception exception) {
            log.error("Error when querying password without folder by name", exception);
            throw new ErroAoConsultarPastaPorNomeException(exception.getMessage());
        }
        return password.map(PasswordWithoutFolderMapper::forDomain);
    }

    public void delete(Integer id) {
        try {
            repository.deleteById(id);
        }catch (Exception exception) {
            log.error("Erro delete password without folder", exception);
            throw new ErroDeletarSenhaException(exception.getMessage());
        }
    }

    public Optional<SenhaSemPasta> consultById(Integer id) {
        Optional<PasswordWithoutFolderEntity> result;
        try {
            result = repository.findById(id);
        }catch (Exception exception) {
            log.error("Error consult by id password without folder");
            throw new ErroConsultarSenhaPorIdException(exception.getMessage());
        }
        return result.map(PasswordWithoutFolderMapper::forDomain);
    }
}
