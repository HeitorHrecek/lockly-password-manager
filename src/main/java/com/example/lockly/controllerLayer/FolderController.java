package com.example.lockly.controllerLayer;

import com.example.lockly.controllerLayer.dtos.FolderDto;
import com.example.lockly.serviceLayer.FolderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/folders")
public class FolderController {

    private final FolderService service;

    @Autowired
    public FolderController(FolderService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<FolderDto> register(@RequestBody FolderDto folderDto) {
        FolderDto savedFolder = service.register(folderDto);
        return new ResponseEntity<>(savedFolder, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FolderDto>> consultAllByUser(@PathVariable Long userId) {
        List<FolderDto> folders = service.consultAllByUser(userId);
        return folders.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(folders, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}/change-name")
    public ResponseEntity<FolderDto> changeName(@RequestBody FolderDto folderDto, @PathVariable Long id) {
        FolderDto updatedFolder = service.changeName(folderDto, id);
        return updatedFolder != null ? new ResponseEntity<>(updatedFolder, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
