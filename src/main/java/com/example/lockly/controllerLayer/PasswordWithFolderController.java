package com.example.lockly.controllerLayer;

import com.example.lockly.controllerLayer.dtos.PasswordFolderIdsDto;
import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithFolderDto;
import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.mapper.passwords.PasswordWithFolderMapper;
import com.example.lockly.mapper.passwords.PasswordWithoutFolderMapper;
import com.example.lockly.serviceLayer.passwords.PasswordWithFolderService;
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
    public ResponseEntity<PasswordWithFolderDto> register(@RequestBody PasswordWithFolderDto newPassword) {
        PasswordWithFolderDto passwordResponse = PasswordWithFolderMapper.forDto(service.register(PasswordWithFolderMapper.forDomainFromDto(newPassword)));
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
    public ResponseEntity<Void> removePasswordFolder(@PathVariable Integer idPasswordWithFolder) {
        service.removePasswordFolder(idPasswordWithFolder);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/consult/all/{idUser}")
    public ResponseEntity<List<PasswordWithFolderDto>> consultAllByUser(@PathVariable Integer idUser) {
        List<PasswordWithFolderDto> result = PasswordWithFolderMapper.forDtos(service.consultAllByUser(idUser));
        return ResponseEntity.ok(result);
    }

    @GetMapping(value = "consult/all/folder/{idFolder}")
    public ResponseEntity<List<PasswordWithFolderDto>> consultAllByFolder(@PathVariable Integer idFolder) {
        List<PasswordWithFolderDto> result = PasswordWithFolderMapper.forDtos(service.consultAllByFolder(idFolder));
        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "/consult/{name}")
    public ResponseEntity<PasswordWithFolderDto> queryByName(@PathVariable String name) {
        PasswordWithFolderDto passwordResult = PasswordWithFolderMapper.forDto(service.queryByName(name));
        return ResponseEntity.ok(passwordResult);
    }

    @GetMapping(value = "/consult/{id}")
    public ResponseEntity<PasswordWithFolderDto> queryById(@PathVariable Integer id) {
        PasswordWithFolderDto passwordResult = PasswordWithFolderMapper.forDto(service.consultById(id));
        return ResponseEntity.ok(passwordResult);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<PasswordWithFolderDto> delete(@PathVariable Integer id) {
        service.deleteWithFolder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/change/{idPassword}")
    public ResponseEntity<PasswordWithFolderDto> change(@RequestBody PasswordWithFolderDto newData, @PathVariable Integer idPassword) {
        PasswordWithFolderDto passwordResult = PasswordWithFolderMapper.forDto(service.change(PasswordWithFolderMapper.forDomainFromDto(newData), idPassword));
        return ResponseEntity.ok(passwordResult);
    }

    @PutMapping("/change-folder")
    public ResponseEntity<PasswordWithFolderDto> changeFolder(@RequestBody PasswordFolderIdsDto ids) {
        PasswordWithFolderDto passwordResult = PasswordWithFolderMapper.forDto(service.changeFolder(ids.idPassword(), ids.idFolder()));
        return ResponseEntity.ok(passwordResult);
    }
}
