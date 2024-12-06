package com.example.lockly.entrypoint.controller;

import com.example.lockly.entrypoint.dtos.FolderDto;
import com.example.lockly.serviceLayer.FolderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/folders")
public class FolderController {

    private final FolderService service;

    @PostMapping("/register")
    public ResponseEntity<FolderDto> register(@RequestBody FolderDto folderDto) {
        FolderDto savedFolder = service.register(folderDto);
        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/folders/{id}")
                                .buildAndExpand(savedFolder.id())
                                .toUri()
                )
                .body(savedFolder);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<FolderDto>> consultAllByUser(@PathVariable Integer userId) {
        List<FolderDto> folders = service.consultAllByUser(userId);
        return ResponseEntity.ok(folders);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<FolderDto> consultByName(@PathVariable String name) {
        FolderDto folder = service.consultByName(name);
        return ResponseEntity.ok(folder);
    }

    @PutMapping("/change-name/{id}")
    public ResponseEntity<FolderDto> changeName(@RequestBody FolderDto folderDto, @PathVariable Integer id) {
        FolderDto updatedFolder = service.changeName(folderDto, id);
        return ResponseEntity.ok(updatedFolder);
    }
}
