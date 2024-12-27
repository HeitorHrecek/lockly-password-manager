package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.application.exceptions.senha.NenhumaSenhaEncontradaException;
import com.example.lockly.application.exceptions.senha.SenhaJaCadastradaException;
import com.example.lockly.application.exceptions.senha.SenhaNaoEncontradaException;
import com.example.lockly.entrypoint.dto.ResponseDto;
import com.example.lockly.infrastructure.dataprovider.exceptions.senha.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class SenhaHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;

    @ExceptionHandler(ErroDeletarSenhaException.class)
    private ResponseEntity<ResponseDto> erroAoDeletarSenhaHandler(ErroDeletarSenhaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroConsultarSenhaPorIdException.class)
    private ResponseEntity<ResponseDto> erroAoConsultarSenhaPorIdHandler(ErroConsultarSenhaPorIdException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroSalvarSenhaException.class)
    private ResponseEntity<ResponseDto> erroAoSalvarSenhaHandler(ErroSalvarSenhaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroListarSenhasPorUsuarioException.class)
    private ResponseEntity<ResponseDto> erroAoListarSenhasPorUsuarioHandler(ErroListarSenhasPorUsuarioException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroAoListarSenhasPorPastaException.class)
    private ResponseEntity<ResponseDto> erroAoConsultarSenhaPorPastaHandler(ErroAoListarSenhasPorPastaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroAoConsultarPastaPorNomeException.class)
    private ResponseEntity<ResponseDto> erroAoConsultarSenhaPorNome(ErroAoConsultarPastaPorNomeException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(NenhumaSenhaEncontradaException.class)
    private ResponseEntity<ResponseDto> senhaNaoEncontradaHandler(NenhumaSenhaEncontradaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_404).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(SenhaJaCadastradaException.class)
    private ResponseEntity<ResponseDto> senhaJaCadastradaHandler(SenhaJaCadastradaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(SenhaNaoEncontradaException.class)
    private ResponseEntity<ResponseDto> senhaNaoEncontradaHandler(SenhaNaoEncontradaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_404).body(ResponseDto.comErro(erroDto));
    }

}
