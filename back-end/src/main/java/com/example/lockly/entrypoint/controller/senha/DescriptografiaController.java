package com.example.lockly.entrypoint.controller.senha;

import com.example.lockly.entrypoint.dto.ResponseDto;
import com.example.lockly.entrypoint.dto.passwords.SenhaComPastaDto;
import com.example.lockly.entrypoint.dto.passwords.SenhaSemPastaDto;
import com.example.lockly.infrastructure.mapper.passwords.PasswordWithFolderMapper;
import com.example.lockly.infrastructure.mapper.passwords.PasswordWithoutFolderMapper;
import com.example.lockly.application.usecases.senha.DescriptografiaSenhaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/senha/descriptografar")
@AllArgsConstructor
public class DescriptografiaController {

    private final DescriptografiaSenhaUseCase service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto<SenhaSemPastaDto>> descriptografarSenhasSemPasta(@PathVariable Integer id) {
        SenhaSemPastaDto result = PasswordWithoutFolderMapper.forDto(service.descriptografiaSemPasta(id));
        ResponseDto<SenhaSemPastaDto> resposta = new ResponseDto<>(result);
        return ResponseEntity.ok(resposta);
    }


    @GetMapping(value = "/pasta/{id}")
    public ResponseEntity<ResponseDto<SenhaComPastaDto>> descriptografarSenhasComPasta(@PathVariable Integer id) {
        SenhaComPastaDto result = PasswordWithFolderMapper.forDto(service.descriptografiaComPasta(id));
        ResponseDto<SenhaComPastaDto> resposta = new ResponseDto<>(result);
        return ResponseEntity.ok(resposta);
    }

}