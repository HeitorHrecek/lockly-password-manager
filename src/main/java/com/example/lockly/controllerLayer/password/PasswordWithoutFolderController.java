package com.example.lockly.controllerLayer.password;

import com.example.lockly.controllerLayer.dtos.ChangeDto;
import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.mapper.passwords.PasswordWithoutFolderMapper;
import com.example.lockly.serviceLayer.passwords.PasswordWithoutFolderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/passwords")
@AllArgsConstructor
public class PasswordWithoutFolderController {

    private final PasswordWithoutFolderService service;

    @PostMapping("/register")
    public ResponseEntity<PasswordWithoutFolderDto> register(@RequestBody PasswordWithoutFolderDto passwordWithoutFolderDto) {
        PasswordWithoutFolderDto passwordResponse = PasswordWithoutFolderMapper.forDto(service.register(PasswordWithoutFolderMapper.forDomainFromDto(passwordWithoutFolderDto)));
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/passwords/add/without-folder/{id}").buildAndExpand(passwordResponse.id()).toUri())
                .body(passwordResponse);
    }

    @GetMapping(value = "/consult/all-by-user/{idUser}")
    public ResponseEntity<List<PasswordWithoutFolderDto>> consultAllByUser(@PathVariable Integer idUser) {
        List<PasswordWithoutFolderDto> passwordWithoutFolderResult = PasswordWithoutFolderMapper.forDtos(service.consultAllByUser(idUser));
        return ResponseEntity.ok(passwordWithoutFolderResult);
    }

    @GetMapping(value = "/consult/by-name/{name}")
    public ResponseEntity<PasswordWithoutFolderDto> queryByName(@PathVariable String name) {
        PasswordWithoutFolderDto passwordResult = PasswordWithoutFolderMapper.forDto(service.queryByName(name));
        return ResponseEntity.ok(passwordResult);
    }

    @GetMapping(value = "/consult/{id}")
    public ResponseEntity<PasswordWithoutFolderDto> queryById(@PathVariable Integer id) {
        PasswordWithoutFolderDto passwordResult = PasswordWithoutFolderMapper.forDto(service.consultById(id));
        return ResponseEntity.ok(passwordResult);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<PasswordWithoutFolderDto> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change-name")
    public ResponseEntity<PasswordWithoutFolderDto> changeName(@RequestBody ChangeDto newData) {
        PasswordWithoutFolderDto passwordResult = PasswordWithoutFolderMapper.forDto(service.changeName(newData.name(), newData.id()));
        return ResponseEntity.ok(passwordResult);
    }
}
