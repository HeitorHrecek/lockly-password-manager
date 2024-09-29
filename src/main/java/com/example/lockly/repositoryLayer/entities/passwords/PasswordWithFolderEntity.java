package com.example.lockly.repositoryLayer.entities.passwords;


import com.example.lockly.repositoryLayer.entities.FolderEntity;
import com.example.lockly.repositoryLayer.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    //@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;
    @ManyToOne
    //@JoinColumn(name = "folder_id", referencedColumnName = "id", nullable = false)
    private FolderEntity folderEntity;

}
