package com.example.lockly.entrypoint.controller;

import com.example.lockly.application.usecases.PastaUseCase;
import com.example.lockly.entrypoint.dto.PastaDto;
import com.example.lockly.entrypoint.dto.ResponseDto;
import com.example.lockly.entrypoint.mapper.PastaMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pastas")
public class PastaController {

    private final PastaUseCase service;

    @PostMapping
    public ResponseEntity<ResponseDto<PastaDto>> cadastrar(@RequestBody PastaDto pastaDto) {
        PastaDto pastaDtoSalva = PastaMapper.paraDto(service.cadastrar(PastaMapper.paraDomain(pastaDto)));
        ResponseDto<PastaDto> resposta = new ResponseDto<>(pastaDtoSalva);
        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/pasta/{id}")
                                .buildAndExpand(pastaDtoSalva.id())
                                .toUri()
                )
                .body(resposta);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ResponseDto<List<PastaDto>>> listarPorUsuario(@PathVariable Integer idUsuario) {
        List<PastaDto> pastaDtos = service.listarPorUsuario(idUsuario).stream().map(PastaMapper::paraDto).toList();
        ResponseDto<List<PastaDto>> resposta = new ResponseDto<>(pastaDtos);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ResponseDto<PastaDto>> consultarPorNome(@PathVariable String nome) {
        PastaDto pastaDto = PastaMapper.paraDto(service.consultarPorNome(nome));
        ResponseDto<PastaDto> resposta = new ResponseDto<>(pastaDto);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PastaDto>> mudarNome(@RequestBody String nome, @PathVariable Integer id) {
        PastaDto novaPastaDto = PastaMapper.paraDto(service.mudarNome(nome, id));
        ResponseDto<PastaDto> resposta = new ResponseDto<>(novaPastaDto);
        return ResponseEntity.ok(resposta);
    }
}
