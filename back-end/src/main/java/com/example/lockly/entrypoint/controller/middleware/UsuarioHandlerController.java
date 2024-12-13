package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.dataproviderLayer.exceptions.user.UserDeleteErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSaveErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSearchByEmailErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSearchByIdErrorException;
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

    @ExceptionHandler(UserSaveErrorException.class)
    private ResponseEntity<MensagemErroException> saveErrorHandler(UserSaveErrorException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(UserSearchByIdErrorException.class)
    private ResponseEntity<MensagemErroException> searchByIdErrorHandler(UserSearchByIdErrorException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(UserSearchByEmailErrorException.class)
    private ResponseEntity<MensagemErroException> searchByEmailErrorHandler(UserSearchByEmailErrorException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(UserDeleteErrorException.class)
    private ResponseEntity<MensagemErroException> deleteErrorHandler(UserDeleteErrorException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }
}
