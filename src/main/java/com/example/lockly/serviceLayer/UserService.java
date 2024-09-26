package com.example.lockly.serviceLayer;

import com.example.lockly.dataproviderLayer.UserDataProvier;
import com.example.lockly.domainLayer.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserDataProvier userDataProvider;

    public User consultById(Long id) {
        return userDataProvider.consultById(id);
    }
}
