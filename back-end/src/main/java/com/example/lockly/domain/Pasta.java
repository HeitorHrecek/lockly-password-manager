package com.example.lockly.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Pasta {
    private Integer id;
    private String nome;
    private Usuario usuario;
}
