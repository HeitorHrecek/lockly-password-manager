package com.example.lockly.domainLayer;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class User {
    private Integer id;
    private String name;
    private String email;
    private String password;

    public void setData(User alteredUser){
        if (alteredUser.getName() != null){
            this.name = alteredUser.name;
        }
        if (alteredUser.getEmail() != null){
            this.email = alteredUser.email;
        }
    }
}
