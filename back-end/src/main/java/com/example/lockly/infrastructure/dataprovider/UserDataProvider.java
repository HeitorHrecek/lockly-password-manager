package com.example.lockly.infrastructure.dataprovider;

import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroDeletarUsuarioException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroSalvarUsuarioException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroConsultarUsuarioPorEmailException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroConsultarUsuarioPorIdException;
import com.example.lockly.domain.Usuario;
import com.example.lockly.infrastructure.mapper.UserMapper;
import com.example.lockly.infrastructure.repositoryLayer.UserRepository;
import com.example.lockly.infrastructure.repositoryLayer.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class UserDataProvider {
    private final UserRepository repository;

    public Usuario save(Usuario newUsuario){
        UserEntity user = UserMapper.forEntity(newUsuario);

        try {
            user = repository.save(user);
        }catch (Exception exception){
            log.error("Error while saving the user.", exception);
            throw new ErroSalvarUsuarioException(exception.getMessage());
        }
        return UserMapper.forDomain(user);
    }

    public Optional<Usuario> searchById(Integer id){
        Optional<UserEntity> result;

        try{
            result = repository.findById(id);
        }catch (Exception exception){
            log.error("Error while searching user by id.", exception);
            throw new ErroConsultarUsuarioPorIdException(exception.getMessage());
        }

        return result.map(UserMapper::forDomain);
    }

    public Optional<Usuario> searchByEmail(String email){
        Optional<UserEntity> result;

        try {
            result = repository.findByEmail(email);
        }catch (Exception exception){
            log.error("Error while searching email.", exception);
            throw new ErroConsultarUsuarioPorEmailException(exception.getMessage());
        }

        return result.map(UserMapper::forDomain);
    }

    public void delete(Integer id){
        try {
            repository.deleteById(id);
        }catch (Exception exception){
            log.error("Error while delete an user.", exception);
            throw new ErroDeletarUsuarioException(exception.getMessage());
        }
    }
}