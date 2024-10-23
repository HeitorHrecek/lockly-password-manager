package com.example.lockly.controllerLayer.middleware;

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
    private ResponseEntity<MessageErrorException> alreadyRegisteredHandler(UserAlreadyRegisteredException exception){
        MessageErrorException message = MessageErrorException.builder()
                .status(STATUS_400)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(EmailPasswordInvalidException.class)
    private ResponseEntity<MessageErrorException> dateInvalidHandler (EmailPasswordInvalidException exception){
        MessageErrorException message = MessageErrorException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<MessageErrorException> notFoundHandler(UserNotFoundException exception){
        MessageErrorException message = MessageErrorException.builder()
                .status(STATUS_404)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserSaveErrorException.class)
    private ResponseEntity<MessageErrorException> saveErrorHandler(UserSaveErrorException exception){
        MessageErrorException message = MessageErrorException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserSearchByIdErrorException.class)
    private ResponseEntity<MessageErrorException> searchByIdErrorHandler(UserSearchByIdErrorException exception){
        MessageErrorException message = MessageErrorException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserSearchByEmailErrorException.class)
    private ResponseEntity<MessageErrorException> searchByEmailErrorHandler(UserSearchByEmailErrorException exception){
        MessageErrorException message = MessageErrorException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }

    @ExceptionHandler(UserDeleteErrorException.class)
    private ResponseEntity<MessageErrorException> deleteErrorHandler(UserDeleteErrorException exception){
        MessageErrorException message = MessageErrorException.builder()
                .status(STATUS_500)
                .message(exception.getMessage())
                .build();

        return ResponseEntity.status(message.status()).body(message);
    }
}
