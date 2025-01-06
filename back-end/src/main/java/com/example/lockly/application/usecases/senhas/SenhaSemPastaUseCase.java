package com.example.lockly.application.usecases.senhas;

import com.example.lockly.application.exceptions.senha.NenhumaSenhaEncontradaException;
import com.example.lockly.application.exceptions.senha.SenhaNaoEncontradaException;
import com.example.lockly.application.gateways.senhas.SenhaSemPastaGateway;
import com.example.lockly.application.usecases.UsuarioUseCase;
import com.example.lockly.domain.senhas.SenhaComPasta;
import com.example.lockly.domain.senhas.SenhaSemPasta;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class SenhaSemPastaUseCase {

    private final SenhaSemPastaGateway gateway;
    private final UsuarioUseCase usuarioUseCase;
    private final CriptografiaUseCase criptografiaUseCase;

    public SenhaSemPasta cadastrar(SenhaSemPasta novaSenha) {
        log.info("Cadastro de senha. senha={}", novaSenha);
        novaSenha.setUsuario(usuarioUseCase.consultarPorId(novaSenha.getUsuario().getId()));
        SenhaEChave senhaEChave = criptografiaUseCase.criptografar(novaSenha.getConteudo());
        novaSenha.setConteudo(senhaEChave.senhaCriptografada());
        novaSenha.setChaveCriptografia(senhaEChave.chave());
        SenhaSemPasta senhaSalva = gateway.salvar(novaSenha);
        log.info("Senha cadastrada com sucesso. senha={}", senhaSalva);
        return senhaSalva;
    }

    public List<SenhaSemPasta> listarPorUsuario(Integer isUsuario) {
        log.info("Listar senhas pelo usuário. id={}", isUsuario);
        List<SenhaSemPasta> senhas = gateway.listarPorUsuario(isUsuario);
        if (senhas.isEmpty()) {
            throw new NenhumaSenhaEncontradaException();
        }

        log.info("Senhas listadas com sucesso. senhas={}", senhas);
        return senhas;
    }

    public SenhaSemPasta consultarPorNome(String nome) {
        log.info("Consultar senha pelo nome. nome={}", nome);
        Optional<SenhaSemPasta> senhaOptional = gateway.consultarPorNome(nome);
        if (senhaOptional.isEmpty()) {
            throw new SenhaNaoEncontradaException();
        }

        SenhaSemPasta senha = senhaOptional.get();

        log.info("Senha consultada com sucesso. senha={}", senha);

        return senha;
    }

    public void deletar(Integer id) {
        log.info("Deleção de senha pelo id. id={}", id);
        SenhaSemPasta senha = consultarPorId(id);
        gateway.deletar(id);
        log.info("Deleção de senha concluida com sucesso. senha={}", senha);
    }

    public SenhaSemPasta mudarNome(String novoNome, Integer id) {
        SenhaSemPasta senha = consultarPorId(id);
        senha.setNome(novoNome);
        return gateway.salvar(senha);
    }

    public SenhaSemPasta consultarPorId(Integer id) {
        Optional<SenhaSemPasta> senha = gateway.consultarPorId(id);
        if (senha.isEmpty())
            throw new SenhaNaoEncontradaException();
        return senha.get();
    }
}
