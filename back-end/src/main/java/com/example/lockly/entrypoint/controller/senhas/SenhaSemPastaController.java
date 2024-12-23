package com.example.lockly.entrypoint.controller.senhas;

import com.example.lockly.application.usecases.senhas.SenhaSemPastaUseCase;
import com.example.lockly.entrypoint.dto.ResponseDto;
import com.example.lockly.entrypoint.dto.senhas.SenhaSemPastaDto;
import com.example.lockly.entrypoint.mapper.senhas.SenhaSemPastaMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/senhas")
@AllArgsConstructor
public class SenhaSemPastaController {

    private final SenhaSemPastaUseCase service;

    @PostMapping
    public ResponseEntity<ResponseDto<SenhaSemPastaDto>> cadastrar(@RequestBody SenhaSemPastaDto novaSenha) {
        SenhaSemPastaDto senhaSalva = SenhaSemPastaMapper.paraDto(service.cadastrar(SenhaSemPastaMapper.paraDomain(novaSenha)));
        ResponseDto<SenhaSemPastaDto> resposta = new ResponseDto<>(senhaSalva);
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/senhas/{id}").buildAndExpand(senhaSalva.id()).toUri())
                .body(resposta);
    }

    @GetMapping(value = "/usuario/{idUsuario}")
    public ResponseEntity<ResponseDto<List<SenhaSemPastaDto>>> listarPorUsuarios(@PathVariable Integer idUsuario) {
        List<SenhaSemPastaDto> senhas = SenhaSemPastaMapper.paraDtos(service.listarPorUsuario(idUsuario));
        ResponseDto<List<SenhaSemPastaDto>> resposta = new ResponseDto<>(senhas);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<ResponseDto<SenhaSemPastaDto>> consultarPorNome(@PathVariable String nome) {
        SenhaSemPastaDto senha = SenhaSemPastaMapper.paraDto(service.consultarPorNome(nome));
        ResponseDto<SenhaSemPastaDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto<SenhaSemPastaDto>> consultarPorId(@PathVariable Integer id) {
        SenhaSemPastaDto senha = SenhaSemPastaMapper.paraDto(service.consultarPorId(id));
        ResponseDto<SenhaSemPastaDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<SenhaSemPastaDto>> mudarNome(@PathVariable Integer id, @RequestBody String novoNome) {
        SenhaSemPastaDto novaSenha = SenhaSemPastaMapper.paraDto(service.mudarNome(novoNome, id));
        ResponseDto<SenhaSemPastaDto> resposta = new ResponseDto<>(novaSenha);
        return ResponseEntity.ok(resposta);
    }
}
