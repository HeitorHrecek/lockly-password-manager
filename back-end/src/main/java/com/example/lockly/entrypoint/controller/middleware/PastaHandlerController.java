package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.application.exceptions.pasta.NenhumaPastaEncontradaException;
import com.example.lockly.application.exceptions.pasta.PastaJaCadastradaException;
import com.example.lockly.entrypoint.dto.ResponseDto;
import com.example.lockly.infrastructure.dataprovider.exceptions.pasta.ErroAoListarPastasPorUsuarioException;
import com.example.lockly.infrastructure.dataprovider.exceptions.pasta.ErroDeletarPastaException;
import com.example.lockly.infrastructure.dataprovider.exceptions.pasta.ErroSalvarPastaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class PastaHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;

    @ExceptionHandler(PastaJaCadastradaException.class)
    public ResponseEntity<ResponseDto> jaCadastradaHandler(PastaJaCadastradaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(NenhumaPastaEncontradaException.class)
    public ResponseEntity<ResponseDto> nenhumaEncontradaHandler(NenhumaPastaEncontradaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_404).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroSalvarPastaException.class)
    public ResponseEntity<ResponseDto> erroSalvarHandler(ErroSalvarPastaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroAoListarPastasPorUsuarioException.class)
    public ResponseEntity<ResponseDto> erroBuscarPorTodasHandler(ErroAoListarPastasPorUsuarioException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroDeletarPastaException.class)
    public ResponseEntity<ResponseDto> erroDeletaHandler(ErroDeletarPastaException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }
}
