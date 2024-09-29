package com.example.lockly.repositoryLayer.entities.passwords;

import com.example.lockly.repositoryLayer.entities.UserEntity;
import jakarta.persistence.*;
import lombok.*;

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
    @ManyToOne
    //@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private UserEntity userEntity;

}
