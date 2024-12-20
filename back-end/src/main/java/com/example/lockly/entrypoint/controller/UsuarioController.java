package com.example.lockly.entrypoint.controller;

import com.example.lockly.application.usecases.UsuarioUseCase;
import com.example.lockly.entrypoint.dto.ResponseDto;
import com.example.lockly.entrypoint.dto.UsuarioDto;
import com.example.lockly.entrypoint.mapper.UsuarioMapper;
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
        UsuarioDto usuarioSalvo = UsuarioMapper.paraDto(service.cadastrar(UsuarioMapper.paraDomain(novoUsuario)));
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
        UsuarioDto usuario = UsuarioMapper.paraDto(service.consultarPorId(id));
        ResponseDto<UsuarioDto> resposta = new ResponseDto<>(usuario);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/email/{email}")
    public ResponseEntity<ResponseDto<UsuarioDto>> consultarPorEmail(@PathVariable String email){
        UsuarioDto usuario = UsuarioMapper.paraDto(service.consultarPorEmail(email));
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
        UsuarioDto novoUsuario = UsuarioMapper.paraDto(service.alterar(UsuarioMapper.paraDomain(novosDados), id));
        ResponseDto<UsuarioDto> resposta = new ResponseDto<>(novoUsuario);
        return ResponseEntity.ok(resposta);
    }
}
