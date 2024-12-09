package com.example.lockly.entrypoint.controller.password;

import com.example.lockly.entrypoint.dtos.ResponseDto;
import com.example.lockly.entrypoint.dtos.passwords.SenhaComPastaDto;
import com.example.lockly.mapper.passwords.PasswordWithFolderMapper;
import com.example.lockly.serviceLayer.passwords.PasswordWithFolderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/senhas/pastas/")
@AllArgsConstructor
public class SenhaComPastaController {

    private final PasswordWithFolderService service;

    @PostMapping
    public ResponseEntity<ResponseDto<SenhaComPastaDto>> cadastrar(@RequestBody SenhaComPastaDto novaSenha) {
        SenhaComPastaDto resultadoNovaSenha = PasswordWithFolderMapper.forDto(service.register(PasswordWithFolderMapper.forDomainFromDto(novaSenha)));
        ResponseDto<SenhaComPastaDto> resposta = new ResponseDto<>(resultadoNovaSenha);
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/senhas/pasta/cadastro/{id}").buildAndExpand(resultadoNovaSenha.id()).toUri())
                .body(resposta);
    }

    @GetMapping(value = "/usuario/{idUsuario}")
    public ResponseEntity<ResponseDto<List<SenhaComPastaDto>>> listarPorUsuario(@PathVariable Integer idUsuario) {
        List<SenhaComPastaDto> senhas = PasswordWithFolderMapper.forDtos(service.consultAllByUser(idUsuario));
        ResponseDto<List<SenhaComPastaDto>> resposta = new ResponseDto<>(senhas);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/pasta/{idPasta}")
    public ResponseEntity<ResponseDto<List<SenhaComPastaDto>>> listarPorPasta(@PathVariable Integer idPasta) {
        List<SenhaComPastaDto> senhas = PasswordWithFolderMapper.forDtos(service.consultAllByFolder(idPasta));
        ResponseDto<List<SenhaComPastaDto>> resposta = new ResponseDto<>(senhas);
        return ResponseEntity.ok(resposta);
    }


    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<ResponseDto<SenhaComPastaDto>> consultarPorNome(@PathVariable String nome) {
        SenhaComPastaDto senha = PasswordWithFolderMapper.forDto(service.queryByName(nome));
        ResponseDto<SenhaComPastaDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto<SenhaComPastaDto>> consultarPorId(@PathVariable Integer id) {
        SenhaComPastaDto senha = PasswordWithFolderMapper.forDto(service.consultById(id));
        ResponseDto<SenhaComPastaDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SenhaComPastaDto> deletar(@PathVariable Integer id) {
        service.deleteWithFolder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<SenhaComPastaDto>> alterarNome(@PathVariable Integer id, @RequestBody String novoNome) {
        SenhaComPastaDto novaSenha = PasswordWithFolderMapper.forDto(service.changeName(novoNome, id));
        ResponseDto<SenhaComPastaDto> resposta = new ResponseDto<>(novaSenha);
        return ResponseEntity.ok(resposta);
    }
}
