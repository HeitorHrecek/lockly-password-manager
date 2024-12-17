package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.application.exceptions.senha.NenhumaSenhaEncontradaException;
import com.example.lockly.application.exceptions.senha.SenhaJaCadastradaException;
import com.example.lockly.application.exceptions.senha.SenhaNaoEncontradaException;
import com.example.lockly.infrastructure.dataprovider.exceptions.senha.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SenhaHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;

    @ExceptionHandler(ErroDeletarSenhaException.class)
    private ResponseEntity<MensagemErroException> erroAoDeletarSenhaHandler(ErroDeletarSenhaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErroConsultarSenhaPorIdException.class)
    private ResponseEntity<MensagemErroException> erroAoConsultarSenhaPorIdHandler(ErroConsultarSenhaPorIdException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErroSalvarSenhaException.class)
    private ResponseEntity<MensagemErroException> erroAoSalvarSenhaHandler(ErroSalvarSenhaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErroListarSenhasPorUsuarioException.class)
    private ResponseEntity<MensagemErroException> erroAoListarSenhasPorUsuarioHandler(ErroListarSenhasPorUsuarioException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErroAoListarSenhasPorPastaException.class)
    private ResponseEntity<MensagemErroException> erroAoConsultarSenhaPorPastaHandler(ErroAoListarSenhasPorPastaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErroAoConsultarPastaPorNomeException.class)
    private ResponseEntity<MensagemErroException> erroAoConsultarSenhaPorNome(ErroAoConsultarPastaPorNomeException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(NenhumaSenhaEncontradaException.class)
    private ResponseEntity<MensagemErroException> senhaNaoEncontradaHandler(NenhumaSenhaEncontradaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(HttpStatus.NOT_FOUND).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(SenhaJaCadastradaException.class)
    private ResponseEntity<MensagemErroException> senhaJaCadastradaHandler(SenhaJaCadastradaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_404).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(SenhaNaoEncontradaException.class)
    private ResponseEntity<MensagemErroException> senhaNaoEncontradaHandler(SenhaNaoEncontradaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_404).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

}
