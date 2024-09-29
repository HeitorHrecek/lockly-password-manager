package com.example.lockly.repositoryLayer.entities;

import jakarta.persistence.*;
import lombok.*;
@Entity(name = "Folder")
@Table(name = "folders")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class FolderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer userId;
    private String name;
    @ManyToOne
    //@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;
}
