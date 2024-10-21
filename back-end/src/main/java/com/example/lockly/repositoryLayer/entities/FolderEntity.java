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
    private String name;
    @ManyToOne
    private UserEntity userEntity;
}
