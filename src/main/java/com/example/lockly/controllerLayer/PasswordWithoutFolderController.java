package com.example.lockly.controllerLayer;

import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.mapper.passwords.PasswordWithoutFolderMapper;
import com.example.lockly.serviceLayer.passwords.PasswordWithoutService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/passwords")
@AllArgsConstructor
public class PasswordWithoutFolderController {

    private final PasswordWithoutService service;

    @PostMapping("/add/without-folder")
    public ResponseEntity<PasswordWithoutFolderDto> registerWithoutFolder(@RequestBody PasswordWithoutFolderDto passwordWithoutFolderDto) {
        PasswordWithoutFolderDto passwordResponse = PasswordWithoutFolderMapper.forDto(service.register(PasswordWithoutFolderMapper.forDomainFromDto(passwordWithoutFolderDto)));
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/passwords/add/without-folder/{id}").buildAndExpand(passwordResponse.id()).toUri())
                .body(passwordResponse);
    }

    @GetMapping(value = "/consult/all-without-folder-by-user/{idUser}")
    public ResponseEntity<List<PasswordWithoutFolderDto>> consultAllWithoutFolderByUser(@PathVariable Long idUser) {
        List<PasswordWithoutFolderDto> passwordWithoutFolderResult = PasswordWithoutFolderMapper.forDtos(service.consultAllByUser(idUser));
        return ResponseEntity.ok(passwordWithoutFolderResult);
    }

    @GetMapping(value = "/consult/by-name-without-folder/{name}")
    public ResponseEntity<PasswordWithoutFolderDto> queryByNameWithoutFolder(@PathVariable String name) {
        PasswordWithoutFolderDto passwordResult = PasswordWithoutFolderMapper.forDto(service.queryByName(name));
        return ResponseEntity.ok(passwordResult);
    }

    @DeleteMapping(value = "/delete/whitout-folder/{id}")
    public ResponseEntity<PasswordWithoutFolderDto> deleteWithoutFolder(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/change/without-folder/{id}")
    public ResponseEntity<PasswordWithoutFolderDto> changeNoFolder(@RequestBody PasswordWithoutFolderDto newPassword, @PathVariable Long id) {
        PasswordWithoutFolderDto passwordResult = PasswordWithoutFolderMapper.forDto(service.change(PasswordWithoutFolderMapper.forDomainFromDto(newPassword), id));
        return ResponseEntity.ok(passwordResult);
    }
}
