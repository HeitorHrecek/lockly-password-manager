package com.example.lockly.repositoryLayer.entities.passwords;

import com.example.lockly.repositoryLayer.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.crypto.SecretKey;

@Entity(name = "PasswordWithoutFolder")
@Table(name = "passwords")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordWithoutFolderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String content;
    private SecretKey encryptionKey;
    @ManyToOne
    private UserEntity userEntity;

}
