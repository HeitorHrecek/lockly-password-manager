package com.example.lockly.domainLayer.passwords;

import com.example.lockly.domainLayer.Folder;
import com.example.lockly.domainLayer.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Builder
public class PasswordWithFolder {
    private Integer id;
    private String name;
    private String content;
    private User user;
    private Folder folder;

    public void setData(PasswordWithFolder newData) {
        if(newData.getName() != null)
            this.name = newData.getName();
        if(newData.getContent() != null)
            this.content = newData.getContent();
    }
}
