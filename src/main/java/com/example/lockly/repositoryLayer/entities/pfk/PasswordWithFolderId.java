package com.example.lockly.repositoryLayer.entities.pfk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordWithFolderId implements Serializable {
    private Integer passwordFolderId;
    private Integer userId;
}
