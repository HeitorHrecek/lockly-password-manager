package com.example.lockly.controllerLayer;

import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithFolderDto;
import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.mapper.PasswordWithFolderMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/passwords/folders/")
@AllArgsConstructor
public class PasswordWithFolderController {

    private final PasswordWithFolderService service;

    @PostMapping("/register")
    public ResponseEntity<PasswordWithFolderDto> registerWithFolder(@RequestBody PasswordWithFolderDto passwordWithFolderDto) {
        PasswordWithFolderDto passwordResponse = PasswordWithFolderMapper.forDto(service.registerWithFolder(PasswordWithFolderMapper.forDomainFromDto(passwordWithFolderDto)));
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/passwords/add/with-pasta/{id}").buildAndExpand(passwordResponse.id()).toUri())
                .body(passwordResponse);
    }

    @PostMapping(value = "/put-in-folder/{idFolder}")
    public ResponseEntity<PasswordWithFolderDto> putInFolder(@RequestBody PasswordWithoutFolderDto passwordWithoutFolder, @PathVariable Long idFolder) {
        PasswordWithFolderDto passwordResponse = PasswordWithFolderMapper.forDto(service.putInFolder(passwordWithoutFolder, idFolder));
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/passwords/put-in-folder/{id}").buildAndExpand(passwordResponse.id()).toUri())
                .body(passwordResponse);
    }

    @DeleteMapping(value = "/remove-password-folder/{idPasswordWithFolder}")
    public ResponseEntity<Void> removePasswordFolder(@PathVariable Long idPasswordWithFolder) {
        service.removePasswordFolder(idPasswordWithFolder);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/consult/all/{idUser}")
    public ResponseEntity<List<PasswordWithFolderDto>> consultAllByUser(@PathVariable Long idUser) {
        List<PasswordWithFolderDto> passwordWithFolderResult = PasswordWithFolderMapper.forDtos(service.consultAllWithFolderByUser(idUser));
        return ResponseEntity.ok(passwordWithFolderResult);
    }


    @GetMapping(value = "/consult/{name}")
    public ResponseEntity<PasswordWithFolderDto> queryByName(@PathVariable String name) {
        PasswordWithFolderDto passwordResult = PasswordWithFolderMapper.forDto(service.queryByNameWithFolder(name));
        return ResponseEntity.ok(passwordResult);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<PasswordWithFolderDto> delete(@PathVariable Long id) {
        service.deleteWithFolder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/change/{id}")
    public ResponseEntity<PasswordWithFolderDto> change(@RequestBody PasswordWithFolderDto newPassword, @PathVariable Long id) {
        PasswordWithFolderDto passwordResult = PasswordWithFolderMapper.forDto(service.changeWithFolder(PasswordWithFolderMapper.forDomainFromDto(newPassword), id));
        return ResponseEntity.ok(passwordResult);
    }
}
