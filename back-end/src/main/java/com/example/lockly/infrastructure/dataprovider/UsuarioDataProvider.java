package com.example.lockly.infrastructure.dataprovider;

import com.example.lockly.application.gateways.UsuarioGateway;
import com.example.lockly.domain.Usuario;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroConsultarUsuarioPorEmailException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroConsultarUsuarioPorIdException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroDeletarUsuarioException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroSalvarUsuarioException;
import com.example.lockly.infrastructure.mapper.UserMapper;
import com.example.lockly.infrastructure.repository.UsuarioRepository;
import com.example.lockly.infrastructure.repository.entities.UsuarioEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class UsuarioDataProvider implements UsuarioGateway {

    private final UsuarioRepository repository;

    @Override
    public Usuario salvar(Usuario novoUsuario) {
        UsuarioEntity usuario = UserMapper.paraEntity(novoUsuario);

        try {
            usuario = repository.save(usuario);
        } catch (Exception exception) {
            log.error("Erro ao salvar usuario.", exception);
            throw new ErroSalvarUsuarioException(exception.getMessage());
        }
        return UserMapper.paraDomain(usuario);
    }

    @Override
    public Optional<Usuario> consultarPorId(Integer id) {
        Optional<UsuarioEntity> usuario;

        try {
            usuario = repository.findById(id);
        } catch (Exception exception) {
            log.error("Erro ao consultar usuario por id", exception);
            throw new ErroConsultarUsuarioPorIdException(exception.getMessage());
        }

        return usuario.map(UserMapper::paraDomain);
    }

    @Override
    public Optional<Usuario> consultarPorEmail(String email) {
        Optional<UsuarioEntity> usuario;

        try {
            usuario = repository.findByEmail(email);
        } catch (Exception exception) {
            log.error("Erro ao consultar usuario por email.", exception);
            throw new ErroConsultarUsuarioPorEmailException(exception.getMessage());
        }

        return usuario.map(UserMapper::paraDomain);
    }

    @Override
    public void deletar(Integer id) {
        try {
            repository.deleteById(id);
        } catch (Exception exception) {
            log.error("Erro ao deletar usuario.", exception);
            throw new ErroDeletarUsuarioException(exception.getMessage());
        }
    }
}