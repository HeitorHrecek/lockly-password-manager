package com.example.lockly.dataproviderLayer;

import com.example.lockly.dataproviderLayer.exceptions.user.DeleteUserErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.SaveUserErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.SearchUserByEmailErrorException;
import com.example.lockly.dataproviderLayer.exceptions.user.SearchUserByIdErrorException;
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
            throw new SaveUserErrorException(exception.getMessage());
        }
        return UserMapper.forDomain(user);
    }

    public Optional<User> searchById(Long id){
        Optional<UserEntity> result;

        try{
            result = repository.findById(id);
        }catch (Exception exception){
            log.error("Error while searching user by id.", exception);
            throw new SearchUserByIdErrorException(exception.getMessage());
        }

        return result.map(UserMapper::forDomain);
    }

    public Optional<User> searchByEmail(String email){
        Optional<UserEntity> result;

        try {
            result = repository.findByEmail(email);
        }catch (Exception exception){
            log.error("Error while searching email.", exception);
            throw new SearchUserByEmailErrorException(exception.getMessage());
        }

        return result.map(UserMapper::forDomain);
    }

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (Exception exception){
            log.error("Error while delete an user.", exception);
            throw new DeleteUserErrorException(exception.getMessage());
        }
    }
}
