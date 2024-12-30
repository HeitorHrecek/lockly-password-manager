package com.example.lockly.application.usecases;

import com.example.lockly.application.exceptions.usuario.UsuarioJaCadastradoException;
import com.example.lockly.application.exceptions.usuario.UsuarioNaoEncontradoException;
import com.example.lockly.application.gateways.UsuarioGateway;
import com.example.lockly.application.usecases.senhas.CriptografiaUseCase;
import com.example.lockly.domain.Usuario;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UsuarioUseCase {

    private final UsuarioGateway gateway;
    private final CriptografiaUseCase criptografiaUseCase;

    public Usuario cadastrar(Usuario novoUsuario) {
        log.info("Cadastrando novo usuario. usuario={}", novoUsuario);

        Optional<Usuario> usuarioExistente = gateway.consultarPorEmail(novoUsuario.getEmail());
        usuarioExistente.ifPresent(user -> {
            throw new UsuarioJaCadastradoException();
        });

        novoUsuario.setSenha(criptografiaUseCase.criptografiaLogin(novoUsuario.getSenha()));

        Usuario usuarioSalvo = gateway.salvar(novoUsuario);

        log.info("Usuario salvo com sucesso. usuario={}", usuarioSalvo);

        return usuarioSalvo;
    }

    public Usuario consultarPorId(Integer id) {
        log.info("Consultar usuario por id. id={}", id);
        Optional<Usuario> usuarioOptional = gateway.consultarPorId(id);
        if (usuarioOptional.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }

        Usuario usuario = usuarioOptional.get();

        log.info("Usuario consultado com sucesso. usuario={}", usuario);

        return usuario;
    }

    public Usuario consultarPorEmail(String email) {
        log.info("Consultar usuario por email. email={}", email);
        Optional<Usuario> usuarioOptional = gateway.consultarPorEmail(email);
        if (usuarioOptional.isEmpty()) {
            throw new UsuarioNaoEncontradoException();
        }

        Usuario usuario = usuarioOptional.get();

        log.info("Usuario consultado com sucesso. usuario={}", usuario);

        return usuario;
    }

    public void deletar(Integer id) {
        log.info("Deletar usuario por id. id={}", id);
        Usuario usuarioDeletar = consultarPorId(id);
        gateway.deletar(id);
        log.info("Usuario deletado com sucesso. usuario={}", usuarioDeletar);
    }

    public Usuario alterar(Usuario novosDados, Integer id) {
        log.info("Alterar dados do usuario. novos dados={}", novosDados);
        log.info("id={}", id);
        Usuario usuario = consultarPorId(id);
        usuario.setData(novosDados);
        Usuario usuarioAlterado = gateway.salvar(usuario);
        log.info("Usuario alterado com sucesso. usuario={}", usuarioAlterado);
        return usuarioAlterado;
    }


}
