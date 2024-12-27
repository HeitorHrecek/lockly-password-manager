package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.application.exceptions.usuario.EmailSenhaInvalidoException;
import com.example.lockly.application.exceptions.usuario.UsuarioJaCadastradoException;
import com.example.lockly.application.exceptions.usuario.UsuarioNaoEncontradoException;
import com.example.lockly.entrypoint.dto.ResponseDto;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroConsultarUsuarioPorEmailException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroConsultarUsuarioPorIdException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroDeletarUsuarioException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroSalvarUsuarioException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class UsuarioHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;
    private final HttpStatus STATUS_400 = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    private ResponseEntity<ResponseDto> alreadyRegisteredHandler(UsuarioJaCadastradoException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_400).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(EmailSenhaInvalidoException.class)
    private ResponseEntity<ResponseDto> dateInvalidHandler(EmailSenhaInvalidoException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_400).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    private ResponseEntity<ResponseDto> notFoundHandler(UsuarioNaoEncontradoException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_404).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroSalvarUsuarioException.class)
    private ResponseEntity<ResponseDto> saveErrorHandler(ErroSalvarUsuarioException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroConsultarUsuarioPorIdException.class)
    private ResponseEntity<ResponseDto> searchByIdErrorHandler(ErroConsultarUsuarioPorIdException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroConsultarUsuarioPorEmailException.class)
    private ResponseEntity<ResponseDto> searchByEmailErrorHandler(ErroConsultarUsuarioPorEmailException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }

    @ExceptionHandler(ErroDeletarUsuarioException.class)
    private ResponseEntity<ResponseDto> deleteErrorHandler(ErroDeletarUsuarioException exception) {
        ResponseDto.ErroDto erroDto = ResponseDto.ErroDto.builder().mensagens(List.of(exception.getMessage())).build();
        return ResponseEntity.status(STATUS_500).body(ResponseDto.comErro(erroDto));
    }
}
