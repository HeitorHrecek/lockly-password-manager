package com.example.lockly.repositoryLayer.entities.pfk;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class FolderId implements Serializable {
    private Integer folderId;
    private Integer userId;
}
