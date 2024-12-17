package com.example.lockly.infrastructure.repositoryLayer.entities.passwords;


import com.example.lockly.infrastructure.repositoryLayer.entities.FolderEntity;
import com.example.lockly.infrastructure.repositoryLayer.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import javax.crypto.SecretKey;

@Entity(name = "PasswordWithFolder")
@Table(name = "passwords_folder")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordWithFolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String content;
    private SecretKey encryptionKey;
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private FolderEntity folderEntity;

}
