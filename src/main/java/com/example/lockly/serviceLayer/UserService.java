package com.example.lockly.serviceLayer;

import com.example.lockly.dataproviderLayer.UserDataProvider;
import com.example.lockly.domainLayer.User;
import com.example.lockly.serviceLayer.exceptions.user.NotFoundUserException;
import com.example.lockly.serviceLayer.exceptions.user.UserAlreadyRegisterException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private final UserDataProvider dataProvider;

    public User register(User newUser){
        Optional<User> resultConsult = dataProvider.searchById(newUser.getId());
        resultConsult.ifPresent(user -> {
            throw new UserAlreadyRegisterException();
        });

        return dataProvider.save(newUser);
    }

    public User consultById(Long id){
        Optional<User> userConsult = dataProvider.searchById(id);
        if (userConsult.isEmpty()){
            throw new NotFoundUserException();
        }

        return userConsult.get();
    }

    public void delete(Long id){
        consultById(id);
        dataProvider.delete(id);
    }

    public User change(User alteredUser){
        User user = consultById(alteredUser.getId());

        user.setData(alteredUser);
        return dataProvider.save(user);
    }
}
