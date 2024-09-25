package com.example.lockly.domainLayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class Folder {
    private Long id;
    private String name;
    private User user;
}
