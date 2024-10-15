package com.example.lockly.controllerLayer.middleware;

import com.example.lockly.dataproviderLayer.exceptions.folder.*;
import com.example.lockly.serviceLayer.exceptions.folder.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class FolderHandlerController {

    @ExceptionHandler(FolderAlreadyRegisteredException.class)
    public ResponseEntity<String> jaCadastradaHandler(FolderAlreadyRegisteredException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(NoFolderFoundException.class)
    public ResponseEntity<String> nenhumaEncontradaHandler(NoFolderFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FolderSaveErrorException.class)
    public ResponseEntity<String> erroSalvarHandler(FolderSaveErrorException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConsultAllFolderByUserErroException.class)
    public ResponseEntity<String> erroBuscarPorTodasHandler(ConsultAllFolderByUserErroException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DeleteFolderErroException.class)
    public ResponseEntity<String> erroDeletaHandler(DeleteFolderErroException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
