package com.example.lockly.repositoryLayer.entities.passwords;


import com.example.lockly.repositoryLayer.entities.FolderEntity;
import com.example.lockly.repositoryLayer.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "PasswordWithFolder")
@Table(name = "tbpasswordfolder")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PasswordWithFolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String content;
    @ManyToOne
    private UserEntity userEntity;
    @ManyToOne
    private FolderEntity folderEntity;

}
