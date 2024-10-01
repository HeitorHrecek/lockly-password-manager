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
    private Integer id;
    private String name;
    private String email;
    private String password;
}
