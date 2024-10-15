package com.example.lockly.controllerLayer;

import com.example.lockly.controllerLayer.dtos.FolderDto;
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

    // Método register ajustado para criar URI e seguir o padrão
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

    // Consultar todas as pastas do usuário
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<FolderDto>> consultAllByUser(@PathVariable Long userId) {
        List<FolderDto> folders = service.consultAllByUser(userId);
        return ResponseEntity.ok(folders);
    }

    // Excluir pasta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build(); // Alterado para 204 No Content
    }

    // Alterar o nome da pasta
    @PutMapping("/{id}/change-name")
    public ResponseEntity<FolderDto> changeName(@RequestBody FolderDto folderDto, @PathVariable Long id) {
        FolderDto updatedFolder = service.changeName(folderDto, id);
        return ResponseEntity.ok(updatedFolder);
    }
}
