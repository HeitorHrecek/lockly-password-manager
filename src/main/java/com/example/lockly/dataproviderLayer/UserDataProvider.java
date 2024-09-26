package com.example.lockly.dataproviderLayer;

import com.example.lockly.domainLayer.User;
import com.example.lockly.repositoryLayer.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDataProvider {
    private final UserRepository repository;

    public User save(User user){

    }

    public User searchById(Long id){

    }

    public void delete(Long id){

    }
}
