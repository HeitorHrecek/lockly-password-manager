package com.example.lockly.serviceLayer.passwords;

import com.example.lockly.dataproviderLayer.PasswordWithoutFolderDataProvider;
import com.example.lockly.domainLayer.passwords.PasswordWithoutFolder;
import com.example.lockly.serviceLayer.UserService;
import com.example.lockly.serviceLayer.exceptions.NoPasswordFoundException;
import com.example.lockly.serviceLayer.exceptions.PasswordNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PasswordWithoutService {

    private final PasswordWithoutFolderDataProvider dataProvider;
    private final UserService userService;

    public PasswordWithoutFolder register(PasswordWithoutFolder newPassword) {
        newPassword.setUser(userService.consultById(newPassword.getUser().getId()));
        newPassword.setContent(Encrypt.encryptPassword(newPassword.getContent()));
        return dataProvider.save(newPassword);
    }

    public List<PasswordWithoutFolder> consultAllByUser(Long idUser) {
        List<PasswordWithoutFolder> result = dataProvider.consultAllByUser(idUser);
        if(result.isEmpty()) {
            throw new NoPasswordFoundException();
        }
        return result;
    }

    public PasswordWithoutFolder queryByName(String name) {
        Optional<PasswordWithoutFolder> result = dataProvider.queryByName(name);
        if(result.isEmpty()) {
            throw new PasswordNotFoundException();
        }
        return result.get();
    }

    public void delete(Long id) {
        consultById(id);
        dataProvider.delete(id);
    }

    public PasswordWithoutFolder change(PasswordWithoutFolder newData, Long id) {
        PasswordWithoutFolder existingPassword = consultById(id);
        if(newData.getUser() != null)
            existingPassword.setUser(newData.getUser());
        existingPassword.setData(existingPassword);
        return dataProvider.save(existingPassword);
    }

    public PasswordWithoutFolder consultById(Long idPasswordWithoutFolder) {
        Optional<PasswordWithoutFolder> result = dataProvider.consultById(idPasswordWithoutFolder);
        if(result.isEmpty())
            throw new PasswordNotFoundException();
        return result.get();
    }
}
