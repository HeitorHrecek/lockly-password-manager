package com.example.lockly.entrypoint.controller.senha;

import com.example.lockly.entrypoint.dto.ResponseDto;
import com.example.lockly.entrypoint.dto.passwords.SenhaComPastaDto;
import com.example.lockly.entrypoint.mapper.senha.SenhaComPastaMapper;
import com.example.lockly.application.usecases.senha.SenhaComPastaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/senhas/pastas/")
@AllArgsConstructor
public class SenhaComPastaController {

    private final SenhaComPastaUseCase service;

    @PostMapping
    public ResponseEntity<ResponseDto<SenhaComPastaDto>> cadastrar(@RequestBody SenhaComPastaDto novaSenha) {
        SenhaComPastaDto resultadoNovaSenha = SenhaComPastaMapper.paraDto(service.cadastrar(SenhaComPastaMapper.paraDomain(novaSenha)));
        ResponseDto<SenhaComPastaDto> resposta = new ResponseDto<>(resultadoNovaSenha);
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/senhas/pasta/cadastro/{id}").buildAndExpand(resultadoNovaSenha.id()).toUri())
                .body(resposta);
    }

    @GetMapping(value = "/usuario/{idUsuario}")
    public ResponseEntity<ResponseDto<List<SenhaComPastaDto>>> listarPorUsuario(@PathVariable Integer idUsuario) {
        List<SenhaComPastaDto> senhas = SenhaComPastaMapper.paraDtos(service.listarPorUsuario(idUsuario));
        ResponseDto<List<SenhaComPastaDto>> resposta = new ResponseDto<>(senhas);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/pasta/{idPasta}")
    public ResponseEntity<ResponseDto<List<SenhaComPastaDto>>> listarPorPasta(@PathVariable Integer idPasta) {
        List<SenhaComPastaDto> senhas = SenhaComPastaMapper.paraDtos(service.listarPorPastas(idPasta));
        ResponseDto<List<SenhaComPastaDto>> resposta = new ResponseDto<>(senhas);
        return ResponseEntity.ok(resposta);
    }


    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<ResponseDto<SenhaComPastaDto>> consultarPorNome(@PathVariable String nome) {
        SenhaComPastaDto senha = SenhaComPastaMapper.paraDto(service.consultarPorNome(nome));
        ResponseDto<SenhaComPastaDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto<SenhaComPastaDto>> consultarPorId(@PathVariable Integer id) {
        SenhaComPastaDto senha = SenhaComPastaMapper.paraDto(service.consultarPorId(id));
        ResponseDto<SenhaComPastaDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<SenhaComPastaDto> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<SenhaComPastaDto>> alterarNome(@PathVariable Integer id, @RequestBody String novoNome) {
        SenhaComPastaDto novaSenha = SenhaComPastaMapper.paraDto(service.mudarNome(novoNome, id));
        ResponseDto<SenhaComPastaDto> resposta = new ResponseDto<>(novaSenha);
        return ResponseEntity.ok(resposta);
    }
}
