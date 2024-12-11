package com.example.lockly.entrypoint.middleware.password;

import com.example.lockly.entrypoint.middleware.MensagemErroException;
import com.example.lockly.dataproviderLayer.exceptions.password.*;
import com.example.lockly.serviceLayer.exceptions.password.NoPasswordFoundException;
import com.example.lockly.serviceLayer.exceptions.password.PasswordAlreadyRegisteredException;
import com.example.lockly.serviceLayer.exceptions.password.PasswordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class SenhaHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;

    @ExceptionHandler(ErrorDeletePassword.class)
    private ResponseEntity<MensagemErroException> errorDeletePasswordHandler(ErrorDeletePassword exception) {
        MensagemErroException message = MensagemErroException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorQueryPasswordByIdException.class)
    private ResponseEntity<MensagemErroException> errorQueryPasswordByIdHandler(ErrorQueryPasswordByIdException exception) {
        MensagemErroException message = MensagemErroException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorSavePasswordException.class)
    private ResponseEntity<MensagemErroException> errorSavePasswordHandler(ErrorSavePasswordException exception) {
        MensagemErroException message = MensagemErroException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorSearchAllPasswordById.class)
    private ResponseEntity<MensagemErroException> errorSearchPasswordAllByIdHandler(ErrorSearchAllPasswordById exception) {
        MensagemErroException message = MensagemErroException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorSearchPasswordByFolderException.class)
    private ResponseEntity<MensagemErroException> errorSearchPasswordByFolderHandler(ErrorSearchPasswordByFolderException exception) {
        MensagemErroException message = MensagemErroException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorSearchPasswordByNameException.class)
    private ResponseEntity<MensagemErroException> errorSearchPasswordByNameHandler(ErrorSearchPasswordByNameException exception) {
        MensagemErroException message = MensagemErroException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(NoPasswordFoundException.class)
    private ResponseEntity<MensagemErroException> noPasswordFoundHandler(NoPasswordFoundException exception) {
        MensagemErroException message = MensagemErroException.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(PasswordAlreadyRegisteredException.class)
    private ResponseEntity<MensagemErroException> passwordAlreadyRegisterHandler(PasswordAlreadyRegisteredException exception) {
        MensagemErroException message = MensagemErroException.builder().status(STATUS_404).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(PasswordNotFoundException.class)
    private ResponseEntity<MensagemErroException> passwordNotFoundHandler(PasswordNotFoundException exception) {
        MensagemErroException message = MensagemErroException.builder().status(STATUS_404).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

}
