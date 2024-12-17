package com.example.lockly.entrypoint.controller;

import com.example.lockly.entrypoint.dto.ResponseDto;
import com.example.lockly.entrypoint.dto.UsuarioDto;
import com.example.lockly.infrastructure.mapper.UserMapper;
import com.example.lockly.application.usecases.UsuarioUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
public class UsuarioController {

    private final UsuarioUseCase service;

    @PostMapping
    public ResponseEntity<ResponseDto<UsuarioDto>> cadastrar(@RequestBody UsuarioDto novoUsuario){
        UsuarioDto usuarioSalvo = UserMapper.forDto(service.cadastrar(UserMapper.forDomainFromDto(novoUsuario)));
        ResponseDto<UsuarioDto> resposta = new ResponseDto<>(usuarioSalvo);
        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/usuario/{id}")
                                .buildAndExpand(usuarioSalvo.id())
                                .toUri()
                )
                .body(resposta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto<UsuarioDto>> consultarPorId(@PathVariable Integer id){
        UsuarioDto usuario = UserMapper.forDto(service.consultarPorId(id));
        ResponseDto<UsuarioDto> resposta = new ResponseDto<>(usuario);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<ResponseDto<UsuarioDto>> consultarPorEmail(@PathVariable String email){
        UsuarioDto usuario = UserMapper.forDto(service.consultarPorEmail(email));
        ResponseDto<UsuarioDto> resposta = new ResponseDto<>(usuario);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<UsuarioDto>> alterar(@RequestBody UsuarioDto novosDados, @PathVariable Integer id) {
        UsuarioDto novoUsuario = UserMapper.forDto(service.alterar(UserMapper.forDomainFromDto(novosDados), id));
        ResponseDto<UsuarioDto> resposta = new ResponseDto<>(novoUsuario);
        return ResponseEntity.ok(resposta);
    }
}
