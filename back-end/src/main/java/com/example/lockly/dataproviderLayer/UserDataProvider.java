package com.example.lockly.dataproviderLayer;

import com.example.lockly.dataproviderLayer.exceptions.user.UserDeleteErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSaveErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSearchByEmailErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.UserSearchByIdErrorException;
import com.example.lockly.domainLayer.User;
import com.example.lockly.mapper.UserMapper;
import com.example.lockly.repositoryLayer.UserRepository;
import com.example.lockly.repositoryLayer.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@AllArgsConstructor
public class UserDataProvider {
    private final UserRepository repository;

    public User save(User newUser){
        UserEntity user = UserMapper.forEntity(newUser);

        try {
            user = repository.save(user);
        }catch (Exception exception){
            log.error("Error while saving the user.", exception);
            throw new UserSaveErrorException(exception.getMessage());
        }
        return UserMapper.forDomain(user);
    }

    public Optional<User> searchById(Long id){
        Optional<UserEntity> result;

        try{
            result = repository.findById(id);
        }catch (Exception exception){
            log.error("Error while searching user by id.", exception);
            throw new UserSearchByIdErrorException(exception.getMessage());
        }

        return result.map(UserMapper::forDomain);
    }

    public Optional<User> searchByEmail(String email){
        Optional<UserEntity> result;

        try {
            result = repository.findByEmail(email);
        }catch (Exception exception){
            log.error("Error while searching email.", exception);
            throw new UserSearchByEmailErrorException(exception.getMessage());
        }

        return result.map(UserMapper::forDomain);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (Exception exception){
            log.error("Error while delete an user.", exception);
            throw new UserDeleteErrorException(exception.getMessage());
        }
    }
}
