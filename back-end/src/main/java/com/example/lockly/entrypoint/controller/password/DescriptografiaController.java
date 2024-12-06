package com.example.lockly.entrypoint.controller.password;

import com.example.lockly.entrypoint.dtos.ResponseDto;
import com.example.lockly.entrypoint.dtos.passwords.PasswordWithFolderDto;
import com.example.lockly.entrypoint.dtos.passwords.PasswordWithoutFolderDto;
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
@RequestMapping("/senha/descriptografar")
@AllArgsConstructor
public class DescriptografiaController {

    @Autowired
    private final PasswordDecryptService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto<PasswordWithoutFolderDto>> descriptografarSenhasSemPasta(@PathVariable Integer id) {
        PasswordWithoutFolderDto result = PasswordWithoutFolderMapper.forDto(service.decryptWithoutFolder(id));
        ResponseDto<PasswordWithoutFolderDto> resposta = new ResponseDto<>(result);
        return ResponseEntity.ok(resposta);
    }


    @GetMapping(value = "/pasta/{id}")
    public ResponseEntity<ResponseDto<PasswordWithFolderDto>> descriptografarSenhasComPasta(@PathVariable Integer id) {
        PasswordWithFolderDto result = PasswordWithFolderMapper.forDto(service.decryptWithFolder(id));
        ResponseDto<PasswordWithFolderDto> resposta = new ResponseDto<>(result);
        return ResponseEntity.ok(resposta);
    }

}
