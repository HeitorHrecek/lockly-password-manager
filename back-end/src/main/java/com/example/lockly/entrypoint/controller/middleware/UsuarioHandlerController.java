package com.example.lockly.entrypoint.controller.middleware;

import com.example.lockly.dataproviderLayer.exceptions.user.UserDeleteErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSaveErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSearchByEmailErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSearchByIdErrorException;
import com.example.lockly.application.exceptions.user.EmailPasswordInvalidException;
import com.example.lockly.application.exceptions.user.UserAlreadyRegisteredException;
import com.example.lockly.application.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsuarioHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;
    private final HttpStatus STATUS_400 = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    private ResponseEntity<MensagemErroException> alreadyRegisteredHandler(UserAlreadyRegisteredException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_400)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(EmailPasswordInvalidException.class)
    private ResponseEntity<MensagemErroException> dateInvalidHandler (EmailPasswordInvalidException exception){
        MensagemErroException mensagem = MensagemErroException.builder()
                .status(STATUS_500)
                .mensagem(exception.getMessage())
                .build();

        return ResponseEntity.status(mensagem.status()).body(mensagem);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<MensagemErroException> notFoundHandler(UserNotFoundException exception){
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
