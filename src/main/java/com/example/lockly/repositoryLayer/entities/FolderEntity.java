package com.example.lockly.repositoryLayer.entities;

import com.example.lockly.repositoryLayer.entities.pfk.FolderId;
import jakarta.persistence.*;
import lombok.*;
@Entity(name = "Folder")
@Table(name = "tbFolder")
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
    //@JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;
}
