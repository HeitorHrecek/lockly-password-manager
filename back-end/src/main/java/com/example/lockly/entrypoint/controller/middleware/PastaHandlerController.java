package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.application.exceptions.pasta.*;
import com.example.lockly.infrastructure.dataprovider.exceptions.pasta.ErroAoListarPastasPorUsuarioException;
import com.example.lockly.infrastructure.dataprovider.exceptions.pasta.ErroDeletarPastaException;
import com.example.lockly.infrastructure.dataprovider.exceptions.pasta.ErroSalvarPastaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PastaHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;

    @ExceptionHandler(PastaJaCadastradaException.class)
    public ResponseEntity<MensagemErroException> jaCadastradaHandler(PastaJaCadastradaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(HttpStatus.BAD_REQUEST).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensagem);
    }

    @ExceptionHandler(NenhumaPastaEncontradaException.class)
    public ResponseEntity<MensagemErroException> nenhumaEncontradaHandler(NenhumaPastaEncontradaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_404).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(STATUS_404).body(mensagem);
    }

    @ExceptionHandler(ErroSalvarPastaException.class)
    public ResponseEntity<MensagemErroException> erroSalvarHandler(ErroSalvarPastaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();
        return ResponseEntity.status(STATUS_500).body(mensagem);
    }

    @ExceptionHandler(ErroAoListarPastasPorUsuarioException.class)
    public ResponseEntity<MensagemErroException> erroBuscarPorTodasHandler(ErroAoListarPastasPorUsuarioException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(STATUS_500).body(mensagem);
    }

    @ExceptionHandler(ErroDeletarPastaException.class)
    public ResponseEntity<MensagemErroException> erroDeletaHandler(ErroDeletarPastaException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(STATUS_500).body(mensagem);
    }
}
