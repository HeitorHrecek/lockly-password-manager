package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.dataproviderLayer.exceptions.folder.*;
import com.example.lockly.application.exceptions.pasta.*;
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

    @ExceptionHandler(FolderSaveErrorException.class)
    public ResponseEntity<MensagemErroException> erroSalvarHandler(FolderSaveErrorException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();
        return ResponseEntity.status(STATUS_500).body(mensagem);
    }

    @ExceptionHandler(ConsultAllFolderByUserErroException.class)
    public ResponseEntity<MensagemErroException> erroBuscarPorTodasHandler(ConsultAllFolderByUserErroException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(STATUS_500).body(mensagem);
    }

    @ExceptionHandler(DeleteFolderErroException.class)
    public ResponseEntity<MensagemErroException> erroDeletaHandler(DeleteFolderErroException exception) {
        MensagemErroException mensagem = MensagemErroException.builder().status(STATUS_500).mensagem(exception.getMessage()).build();
        return ResponseEntity.status(STATUS_500).body(mensagem);
    }
}
