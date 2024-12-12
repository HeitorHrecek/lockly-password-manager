package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.dataproviderLayer.exceptions.password.*;
import com.example.lockly.application.exceptions.password.NoPasswordFoundException;
import com.example.lockly.application.exceptions.password.PasswordAlreadyRegisteredException;
import com.example.lockly.application.exceptions.password.PasswordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SenhaHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;

    @ExceptionHandler(ErrorDeletePassword.class)
    private ResponseEntity<MensagemErroException> erroAoDeletarSenhaHandler(ErrorDeletePassword exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErrorQueryPasswordByIdException.class)
    private ResponseEntity<MensagemErroException> erroAoConsultarSenhaPorIdHandler(ErrorQueryPasswordByIdException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErrorSavePasswordException.class)
    private ResponseEntity<MensagemErroException> erroAoSalvarSenhaHandler(ErrorSavePasswordException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErrorSearchAllPasswordById.class)
    private ResponseEntity<MensagemErroException> erroAoListarSenhasPorUsuarioHandler(ErrorSearchAllPasswordById exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErrorSearchPasswordByFolderException.class)
    private ResponseEntity<MensagemErroException> erroAoConsultarSenhaPorPastaHandler(ErrorSearchPasswordByFolderException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErrorSearchPasswordByNameException.class)
    private ResponseEntity<MensagemErroException> erroAoConsultarSenhaPorNome(ErrorSearchPasswordByNameException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(NoPasswordFoundException.class)
    private ResponseEntity<MensagemErroException> senhaNaoEncontradaHandler(NoPasswordFoundException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(HttpStatus.NOT_FOUND).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(PasswordAlreadyRegisteredException.class)
    private ResponseEntity<MensagemErroException> senhaJaCadastradaHandler(PasswordAlreadyRegisteredException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_404).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(PasswordNotFoundException.class)
    private ResponseEntity<MensagemErroException> senhaNaoEncontradaHandler(PasswordNotFoundException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_404).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

}
