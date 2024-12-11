package com.example.lockly.entrypoint.middleware;

import com.example.lockly.dataproviderLayer.exceptions.user.UserDeleteErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSaveErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSearchByEmailErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSearchByIdErrorException;
import com.example.lockly.serviceLayer.exceptions.user.EmailPasswordInvalidException;
import com.example.lockly.serviceLayer.exceptions.user.UserAlreadyRegisteredException;
import com.example.lockly.serviceLayer.exceptions.user.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserHandlerController {

    private final HttpStatus STATUS_500 = HttpStatus.INTERNAL_SERVER_ERROR;
    private final HttpStatus STATUS_404 = HttpStatus.NOT_FOUND;
    private final HttpStatus STATUS_400 = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(UserAlreadyRegisteredException.class)
    private ResponseEntity<MensagemErroException> alreadyRegisteredHandler(UserAlreadyRegisteredException exception){
        MensagemErroException message = MensagemErroException.builder()
                .status(STATUS_400)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(EmailPasswordInvalidException.class)
    private ResponseEntity<MensagemErroException> dateInvalidHandler (EmailPasswordInvalidException exception){
        MensagemErroException message = MensagemErroException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<MensagemErroException> notFoundHandler(UserNotFoundException exception){
        MensagemErroException message = MensagemErroException.builder()
                .status(STATUS_404)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserSaveErrorException.class)
    private ResponseEntity<MensagemErroException> saveErrorHandler(UserSaveErrorException exception){
        MensagemErroException message = MensagemErroException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserSearchByIdErrorException.class)
    private ResponseEntity<MensagemErroException> searchByIdErrorHandler(UserSearchByIdErrorException exception){
        MensagemErroException message = MensagemErroException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserSearchByEmailErrorException.class)
    private ResponseEntity<MensagemErroException> searchByEmailErrorHandler(UserSearchByEmailErrorException exception){
        MensagemErroException message = MensagemErroException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserDeleteErrorException.class)
    private ResponseEntity<MensagemErroException> deleteErrorHandler(UserDeleteErrorException exception){
        MensagemErroException message = MensagemErroException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }
}
