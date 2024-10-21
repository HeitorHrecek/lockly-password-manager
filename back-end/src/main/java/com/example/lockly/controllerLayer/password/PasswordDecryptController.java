package com.example.lockly.controllerLayer.password;

import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithFolderDto;
import com.example.lockly.controllerLayer.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.mapper.passwords.PasswordWithFolderMapper;
import com.example.lockly.mapper.passwords.PasswordWithoutFolderMapper;
import com.example.lockly.serviceLayer.passwords.PasswordDecryptService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/password/decrypt")
@AllArgsConstructor
public class PasswordDecryptController {

    @Autowired
    private final PasswordDecryptService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<PasswordWithoutFolderDto> decryptWithoutFolder(@PathVariable Integer id) {
        PasswordWithoutFolderDto result = PasswordWithoutFolderMapper.forDto(service.decryptWithoutFolder(id));
        return ResponseEntity.ok(result);
    }


    @GetMapping(value = "/folder/{id}")
    public ResponseEntity<PasswordWithFolderDto> decryptWithFolder(@PathVariable Integer id) {
        PasswordWithFolderDto result = PasswordWithFolderMapper.forDto(service.decryptWithFolder(id));
        return ResponseEntity.ok(result);
    }

}
