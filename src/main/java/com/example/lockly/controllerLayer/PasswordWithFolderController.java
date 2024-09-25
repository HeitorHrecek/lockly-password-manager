package com.example.lockly.controllerLayer;

import com.example.lockly.controllerLayer.dtos.PasswordFolderIdsDto;
import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithFolderDto;
import com.example.lockly.mapper.passwords.PasswordWithFolderMapper;
import com.example.lockly.serviceLayer.PasswordWithFolderService;
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
    public ResponseEntity<PasswordWithFolderDto> registerWithFolder(@RequestBody PasswordWithFolderDto newPassword) {
        PasswordWithFolderDto passwordResponse = PasswordWithFolderMapper.forDto(service.registerWithFolder(PasswordWithFolderMapper.forDomainFromDto(newPassword)));
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/passwords/add/with-pasta/{id}").buildAndExpand(passwordResponse.id()).toUri())
                .body(passwordResponse);
    }

    @PostMapping("/put-in-folder")
    public ResponseEntity<PasswordWithFolderDto> putInFolder(@RequestBody PasswordFolderIdsDto ids) {
        PasswordWithFolderDto passwordResponse = PasswordWithFolderMapper.forDto(service.putInFolder(ids.idPassword(), ids.idFolder()));
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

    @PutMapping("/change")
    public ResponseEntity<PasswordWithFolderDto> change(@RequestBody PasswordFolderIdsDto ids) {
        PasswordWithFolderDto passwordResult = PasswordWithFolderMapper.forDto(service.changeWithFolder(ids.idPassword(), ids.idFolder()));
        return ResponseEntity.ok(passwordResult);
    }
}
