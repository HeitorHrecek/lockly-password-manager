package com.example.lockly.entrypoint.controller.password;

import com.example.lockly.entrypoint.dtos.ResponseDto;
import com.example.lockly.entrypoint.dtos.passwords.PasswordWithoutFolderDto;
import com.example.lockly.mapper.passwords.PasswordWithoutFolderMapper;
import com.example.lockly.serviceLayer.passwords.PasswordWithoutFolderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/senhas")
@AllArgsConstructor
public class SenhaSemPastaController {

    private final PasswordWithoutFolderService service;

    @PostMapping
    public ResponseEntity<ResponseDto<PasswordWithoutFolderDto>> cadastrar(@RequestBody PasswordWithoutFolderDto novaSenha) {
        PasswordWithoutFolderDto senhaSalva = PasswordWithoutFolderMapper
                .forDto(service.register(PasswordWithoutFolderMapper.forDomainFromDto(novaSenha)));
        ResponseDto<PasswordWithoutFolderDto> resposta = new ResponseDto<>(senhaSalva);
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/senhas/{id}").buildAndExpand(senhaSalva.id()).toUri())
                .body(resposta);
    }

    @GetMapping(value = "/usuario/{idUsuario}")
    public ResponseEntity<ResponseDto<List<PasswordWithoutFolderDto>>> listarPorUsuarios(@PathVariable Integer idUsuario) {
        List<PasswordWithoutFolderDto> senhas = PasswordWithoutFolderMapper.forDtos(service.consultAllByUser(idUsuario));
        ResponseDto<List<PasswordWithoutFolderDto>> resposta = new ResponseDto<>(senhas);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<ResponseDto<PasswordWithoutFolderDto>> consultarPorNome(@PathVariable String nome) {
        PasswordWithoutFolderDto senha = PasswordWithoutFolderMapper.forDto(service.queryByName(nome));
        ResponseDto<PasswordWithoutFolderDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto<PasswordWithoutFolderDto>> consultarPorId(@PathVariable Integer id) {
        PasswordWithoutFolderDto senha = PasswordWithoutFolderMapper.forDto(service.consultById(id));
        ResponseDto<PasswordWithoutFolderDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PasswordWithoutFolderDto>> mudarNome(@PathVariable Integer id, @RequestBody String novoNome) {
        PasswordWithoutFolderDto novaSenha = PasswordWithoutFolderMapper.forDto(service.changeName(novoNome, id));
        ResponseDto<PasswordWithoutFolderDto> resposta = new ResponseDto<>(novaSenha);
        return ResponseEntity.ok(resposta);
    }
}
