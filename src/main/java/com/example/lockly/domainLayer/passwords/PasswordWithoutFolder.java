package com.example.lockly.domainLayer.passwords;

import com.example.lockly.domainLayer.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasswordWithoutFolder {
    private Long id;
    private String name;
    private String content;
    private User user;

    public void setData(PasswordWithoutFolder newData) {
        if(newData.getName() != null)
            this.name = newData.getName();
        if(newData.getContent() != null)
            this.content = newData.getContent();
    }
}
