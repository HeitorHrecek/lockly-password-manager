package com.example.lockly.entrypoint.controller;

import com.example.lockly.entrypoint.dto.LoginDto;
import com.example.lockly.application.usecases.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginUser) {
        service.login(loginUser.email(), loginUser.senha());
        return ResponseEntity.ok().build();
    }
}
