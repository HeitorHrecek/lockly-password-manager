package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroDeletarUsuarioException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroSalvarUsuarioException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroConsultarUsuarioPorEmailException;
import com.example.lockly.infrastructure.dataprovider.exceptions.usuario.ErroConsultarUsuarioPorIdException;
import com.example.lockly.application.exceptions.usuario.EmailSenhaInvalidoException;
import com.example.lockly.application.exceptions.usuario.UsuarioJaCadastradoException;
import com.example.lockly.application.exceptions.usuario.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsuarioHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;
    private final HttpStatus STATUS_400 = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(UsuarioJaCadastradoException.class)
    private ResponseEntity<MensagemErroException> alreadyRegisteredHandler(UsuarioJaCadastradoException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_400)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(EmailSenhaInvalidoException.class)
    private ResponseEntity<MensagemErroException> dateInvalidHandler (EmailSenhaInvalidoException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    private ResponseEntity<MensagemErroException> notFoundHandler(UsuarioNaoEncontradoException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_404)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErroSalvarUsuarioException.class)
    private ResponseEntity<MensagemErroException> saveErrorHandler(ErroSalvarUsuarioException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErroConsultarUsuarioPorIdException.class)
    private ResponseEntity<MensagemErroException> searchByIdErrorHandler(ErroConsultarUsuarioPorIdException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErroConsultarUsuarioPorEmailException.class)
    private ResponseEntity<MensagemErroException> searchByEmailErrorHandler(ErroConsultarUsuarioPorEmailException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(ErroDeletarUsuarioException.class)
    private ResponseEntity<MensagemErroException> deleteErrorHandler(ErroDeletarUsuarioException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }
}
