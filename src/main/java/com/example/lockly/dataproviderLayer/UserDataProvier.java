package com.example.lockly.dataproviderLayer;

import com.example.lockly.domainLayer.User;
import com.example.lockly.mapper.UserMapper;
import com.example.lockly.repositoryLayer.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@AllArgsConstructor
public class UserDataProvier {

    private final UserRepository repository;


    public User consultById(Long id) {
        return UserMapper.forDomain(repository.findById(id).get());
    }
}