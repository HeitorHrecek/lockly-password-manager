package com.example.lockly.domainLayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Long id;
    private String nome;
    private String email;
    private String senha;
}
