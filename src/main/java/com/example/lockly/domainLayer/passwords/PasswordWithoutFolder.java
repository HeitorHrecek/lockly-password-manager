package com.example.lockly.domainLayer.passwords;

import com.example.lockly.domainLayer.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasswordWithoutFolder {
    private Long id;
    private String nome;
    private String conteudo;
    private User user;
}
