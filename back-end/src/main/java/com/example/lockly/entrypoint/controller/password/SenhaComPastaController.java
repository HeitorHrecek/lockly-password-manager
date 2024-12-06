package com.example.lockly.entrypoint.controller.password;

import com.example.lockly.entrypoint.dtos.ChangeDto;
import com.example.lockly.entrypoint.dtos.ResponseDto;
import com.example.lockly.entrypoint.dtos.passwords.PasswordWithFolderDto;
import com.example.lockly.mapper.passwords.PasswordWithFolderMapper;
import com.example.lockly.serviceLayer.passwords.PasswordWithFolderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/passwords/folders/")
@AllArgsConstructor
public class SenhaComPastaController {

    private final PasswordWithFolderService service;

    @PostMapping
    public ResponseEntity<ResponseDto<PasswordWithFolderDto>> cadastrar(@RequestBody PasswordWithFolderDto novaSenha) {
        PasswordWithFolderDto resultadoNovaSenha = PasswordWithFolderMapper.forDto(service.register(PasswordWithFolderMapper.forDomainFromDto(novaSenha)));
        ResponseDto<PasswordWithFolderDto> resposta = new ResponseDto<>(resultadoNovaSenha);
        return ResponseEntity
                .created(UriComponentsBuilder.newInstance().path("/senhas/pasta/cadastro/{id}").buildAndExpand(resultadoNovaSenha.id()).toUri())
                .body(resposta);
    }

    @GetMapping(value = "/usuario/{idUsuario}")
    public ResponseEntity<ResponseDto<List<PasswordWithFolderDto>>> consultarTodosPorUsuario(@PathVariable Integer idUsuario) {
        List<PasswordWithFolderDto> senhas = PasswordWithFolderMapper.forDtos(service.consultAllByUser(idUsuario));
        ResponseDto<List<PasswordWithFolderDto>> resposta = new ResponseDto<>(senhas);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/pasta/{idPasta}")
    public ResponseEntity<ResponseDto<List<PasswordWithFolderDto>>> consultarTodosPorPasta(@PathVariable Integer idPasta) {
        List<PasswordWithFolderDto> senhas = PasswordWithFolderMapper.forDtos(service.consultAllByFolder(idPasta));
        ResponseDto<List<PasswordWithFolderDto>> resposta = new ResponseDto<>(senhas);
        return ResponseEntity.ok(resposta);
    }


    @GetMapping(value = "/nome/{nome}")
    public ResponseEntity<ResponseDto<PasswordWithFolderDto>> consultarPorNome(@PathVariable String nome) {
        PasswordWithFolderDto senha = PasswordWithFolderMapper.forDto(service.queryByName(nome));
        ResponseDto<PasswordWithFolderDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ResponseDto<PasswordWithFolderDto>> consultarPorId(@PathVariable Integer id) {
        PasswordWithFolderDto senha = PasswordWithFolderMapper.forDto(service.consultById(id));
        ResponseDto<PasswordWithFolderDto> resposta = new ResponseDto<>(senha);
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<PasswordWithFolderDto> delete(@PathVariable Integer id) {
        service.deleteWithFolder(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping()
    public ResponseEntity<ResponseDto<PasswordWithFolderDto>> alterarNome(@RequestBody ChangeDto novosDados) {
        PasswordWithFolderDto novaSenha = PasswordWithFolderMapper.forDto(service.changeName(novosDados.name(), novosDados.id()));
        ResponseDto<PasswordWithFolderDto> resposta = new ResponseDto<>(novaSenha);
        return ResponseEntity.ok(resposta);
    }
}
