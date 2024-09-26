package com.example.lockly.repositoryLayer.entities.passwords;

import com.example.lockly.repositoryLayer.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "PasswordWithoutFolder")
@Table(name = "tbpassword")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordWithoutFolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    @ManyToOne
    private UserEntity userEntity;

}
