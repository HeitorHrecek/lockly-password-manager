package com.example.lockly.controllerLayer.middleware;

import com.example.lockly.dataproviderLayer.exceptions.password.*;
import com.example.lockly.serviceLayer.exceptions.password.NoPasswordFoundException;
import com.example.lockly.serviceLayer.exceptions.password.PasswordAlreadyRegisteredException;
import com.example.lockly.serviceLayer.exceptions.password.PasswordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PasswordHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;

    @ExceptionHandler(ErrorDeletePassword.class)
    private ResponseEntity<MessageErrorException> errorDeletePasswordHandler(ErrorDeletePassword exception) {
        MessageErrorException message = MessageErrorException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorQueryPasswordByIdException.class)
    private ResponseEntity<MessageErrorException> errorQueryPasswordByIdHandler(ErrorQueryPasswordByIdException exception) {
        MessageErrorException message = MessageErrorException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorSavePasswordException.class)
    private ResponseEntity<MessageErrorException> errorSavePasswordHandler(ErrorSavePasswordException exception) {
        MessageErrorException message = MessageErrorException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorSearchPasswordAllByIdException.class)
    private ResponseEntity<MessageErrorException> errorSearchPasswordAllByIdHandler(ErrorSearchPasswordAllByIdException exception) {
        MessageErrorException message = MessageErrorException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorSearchPasswordByFolderException.class)
    private ResponseEntity<MessageErrorException> errorSearchPasswordByFolderHandler(ErrorSearchPasswordByFolderException exception) {
        MessageErrorException message = MessageErrorException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(ErrorSearchPasswordByNameException.class)
    private ResponseEntity<MessageErrorException> errorSearchPasswordByNameHandler(ErrorSearchPasswordByNameException exception) {
        MessageErrorException message = MessageErrorException.builder().status(STATUS_500).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(NoPasswordFoundException.class)
    private ResponseEntity<MessageErrorException> noPasswordFoundHandler(NoPasswordFoundException exception) {
        MessageErrorException message = MessageErrorException.builder().status(HttpStatus.NOT_FOUND).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(PasswordAlreadyRegisteredException.class)
    private ResponseEntity<MessageErrorException> passwordAlreadyRegisterHandler(PasswordAlreadyRegisteredException exception) {
        MessageErrorException message = MessageErrorException.builder().status(STATUS_404).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(PasswordNotFoundException.class)
    private ResponseEntity<MessageErrorException> passwordNotFoundHandler(PasswordNotFoundException exception) {
        MessageErrorException message = MessageErrorException.builder().status(STATUS_404).message(exception.getMessage()).build();
        return ResponseEntity.status(message.status()).body(message);
    }

}
