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
public class PasswordWithoutFolderId implements Serializable {
    private Integer passwordId;
    private Integer userId;
}
