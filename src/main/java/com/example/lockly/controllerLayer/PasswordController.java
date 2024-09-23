package com.example.lockly.controllerLayer;

import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithFolderDto;
import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.mapper.PasswordMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/passwords")
@AllArgsConstructor
public class PasswordController {

    private final PasswordService service;

    @PostMapping("/add/without-folder")
    public ResponseEntity<PasswordWithoutFolderDto> registerWithoutFolder(@RequestBody PasswordWithoutFolderDto passwordWithoutFolderDto) {
        PasswordWithoutFolderDto passwordResponse = PasswordMapper.toDto(service.registerWithoutFolder(PasswordMapper.toDomain(passwordWithoutFolderDto)));
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/passwords/add/without-folder/{id}").buildAndExpand(passwordResponse.id()).toUri())
                .body(passwordResponse);
    }

    @PostMapping("/add/with-pasta")
    public ResponseEntity<PasswordWithFolderDto> registerWithFolder(@RequestBody PasswordWithFolderDto passwordWithFolderDto) {
        PasswordWithFolderDto passwordResponse = PasswordMapper.toDto(service.registerWithFolder(PasswordMapper.toDomain(passwordWithFolderDto)));
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/passwords/add/with-pasta/{id}").buildAndExpand(passwordResponse.id()).toUri())
                .body(passwordResponse);
    }

    @PostMapping(value = "/put-in-folder/{idFolder}")
    public ResponseEntity<PasswordWithFolderDto> putInFolder(@RequestBody PasswordWithoutFolderDto passwordWithoutFolder, @PathVariable Long idFolder) {
        PasswordWithFolderDto passwordResponse = PasswordMapper.toDto(service.putInFolder(passwordWithoutFolder, idFolder));
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/passwords/put-in-folder/{id}").buildAndExpand(passwordResponse.id()).toUri())
                .body(passwordResponse);
    }

    @DeleteMapping(value = "/remove-password-folder/{idPasswordWithFolder}")
    public ResponseEntity<Void> removePasswordFolder(@PathVariable Long idPasswordWithFolder) {
        service.removePasswordFolder(idPasswordWithFolder);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/consult/all-without-folder-by-user/{idUser}")
    public ResponseEntity<List<PasswordWithoutFolderDto>> consultAllWithoutFolderByUser(@PathVariable Long idUser) {
        List<PasswordWithoutFolderDto> passwordWithoutFolderResult = PasswordMapper.toDtos(service.consultAllWithoutFolderByUser(idUser));
        return ResponseEntity.ok(passwordWithoutFolderResult);
    }

    @GetMapping(value = "/consult/all-with-folder-by-user/{idUser}")
    public ResponseEntity<List<PasswordWithFolderDto>> consultAllWithFolderByUser(@PathVariable Long idUser) {
        List<PasswordWithFolderDto> passwordWithFolderResult = PasswordMapper.toDtos(service.consultAllWithFolderByUser(idUser));
        return ResponseEntity.ok(passwordWithFolderResult);
    }

    @GetMapping(value = "/consult/by-name-without-folder/{name}")
    public ResponseEntity<PasswordWithoutFolderDto> queryByNameWithoutFolder(@PathVariable String name) {
        PasswordWithoutFolderDto passwordResult = PasswordMapper.toDto(service.queryByNameWithoutFolder(name));
        return ResponseEntity.ok(passwordResult);
    }

    @GetMapping(value = "/consult/by-name-with-folder/{name}")
    public ResponseEntity<PasswordWithFolderDto> queryByNameWithFolder(@PathVariable String name) {
        PasswordWithFolderDto passwordResult = PasswordMapper.toDto(service.queryByNameWithFolder(name));
        return ResponseEntity.ok(passwordResult);
    }

    @DeleteMapping(value = "/delete/whitout-folder/{id}")
    public ResponseEntity<PasswordWithoutFolderDto> deleteWithoutFolder(@PathVariable Long id) {
        service.deleteWithoutFolder(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/delete/whit-folder/{id}")
    public ResponseEntity<PasswordWithFolderDto> deleteWithFolder(@PathVariable Long id) {
        service.deleteWithFolder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/change/without-folder/{id}")
    public ResponseEntity<PasswordWithoutFolderDto> changeNoFolder(@RequestBody PasswordWithoutFolderDto newPassword, @PathVariable Long id) {
        PasswordWithoutFolderDto passwordResult = PasswordMapper.toDto(service.changeNoFolder(PasswordMapper.toDomain(newPassword), id));
        return ResponseEntity.ok(passwordResult);
    }

    @PutMapping(value = "/change/with-folder/{id}")
    public ResponseEntity<PasswordWithFolderDto> changeWithFolder(@RequestBody PasswordWithFolderDto newPassword, @PathVariable Long id) {
        PasswordWithFolderDto passwordResult = PasswordMapper.toDto(service.changeWithFolder(PasswordMapper.toDomain(newPassword), id));
        return ResponseEntity.ok(passwordResult);
    }
}
