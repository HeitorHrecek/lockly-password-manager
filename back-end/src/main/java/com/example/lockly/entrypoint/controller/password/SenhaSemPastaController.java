package com.example.lockly.entrypoint.controller.password;

import com.example.lockly.entrypoint.dtos.ResponseDto;
import com.example.lockly.entrypoint.dtos.passwords.SenhaSemPastaDto;
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
    public ResponseEntity<ResponseDto<SenhaSemPastaDto>> cadastrar(@RequestBody SenhaSemPastaDto novaSenha) {
        SenhaSemPastaDto senhaSalva = PasswordWithoutFolderMapper
                .forDto(service.register(PasswordWithoutFolderMapper.forDomainFromDto(novaSenha)));
        ResponseDto<SenhaSemPastaDto> resposta = new ResponseDto<>(senhaSalva);
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/senhas/{id}").buildAndExpand(senhaSalva.id()).toUri())
                .body(resposta);
    }

    @GetMapping(value = "/usuario/{idUsuario}")
    public ResponseEntity<ResponseDto<List<SenhaSemPastaDto>>> listarPorUsuarios(@PathVariable Integer idUsuario) {
        List<SenhaSemPastaDto> senhas = PasswordWithoutFolderMapper.forDtos(service.consultAllByUser(idUsuario));
        ResponseDto<List<SenhaSemPastaDto>> resposta = new ResponseDto<>(senhas);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<ResponseDto<SenhaSemPastaDto>> consultarPorNome(@PathVariable String nome) {
        SenhaSemPastaDto senha = PasswordWithoutFolderMapper.forDto(service.queryByName(nome));
        ResponseDto<SenhaSemPastaDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto<SenhaSemPastaDto>> consultarPorId(@PathVariable Integer id) {
        SenhaSemPastaDto senha = PasswordWithoutFolderMapper.forDto(service.consultById(id));
        ResponseDto<SenhaSemPastaDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<SenhaSemPastaDto>> mudarNome(@PathVariable Integer id, @RequestBody String novoNome) {
        SenhaSemPastaDto novaSenha = PasswordWithoutFolderMapper.forDto(service.changeName(novoNome, id));
        ResponseDto<SenhaSemPastaDto> resposta = new ResponseDto<>(novaSenha);
        return ResponseEntity.ok(resposta);
    }
}
