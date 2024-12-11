package com.example.lockly.entrypoint.controller;

import com.example.lockly.entrypoint.dtos.PastaDto;
import com.example.lockly.entrypoint.dtos.ResponseDto;
import com.example.lockly.serviceLayer.FolderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/pastas")
public class PastaController {

    private final FolderService service;

    @PostMapping
    public ResponseEntity<ResponseDto<PastaDto>> cadastrar(@RequestBody PastaDto pastaDto) {
        PastaDto pastaSalva = service.register(pastaDto);
        ResponseDto<PastaDto> resposta = new ResponseDto<>(pastaSalva);
        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/pasta/{id}")
                                .buildAndExpand(pastaSalva.id())
                                .toUri()
                )
                .body(resposta);
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<ResponseDto<List<PastaDto>>> listarPorUsuario(@PathVariable Integer idUsuario) {
        List<PastaDto> pastas = service.consultAllByUser(idUsuario);
        ResponseDto<List<PastaDto>> resposta = new ResponseDto<>(pastas);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<ResponseDto<PastaDto>> consultarPorNome(@PathVariable String nome) {
        PastaDto pasta = service.consultByName(nome);
        ResponseDto<PastaDto> resposta = new ResponseDto<>(pasta);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<PastaDto>> mudarNome(@RequestBody String nome, @PathVariable Integer id) {
        PastaDto novaPasta = service.changeName(nome, id);
        ResponseDto<PastaDto> resposta = new ResponseDto<>(novaPasta);
        return ResponseEntity.ok(resposta);
    }
}
