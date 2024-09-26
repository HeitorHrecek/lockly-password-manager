package com.example.lockly.repositoryLayer.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "User")
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
}
