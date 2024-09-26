package com.example.lockly.serviceLayer;

import com.example.lockly.dataproviderLayer.UserDataProvider;
import com.example.lockly.domainLayer.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserService {
    private final UserDataProvider dataProvider;

    public User register(User user){

    }

    public User consultById(Long id){

    }

    public void delete(Long id){

    }

    public User change(User user){

    }
}
