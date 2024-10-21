package com.example.lockly.controllerLayer;

import com.example.lockly.controllerLayer.dtos.LoginDto;
import com.example.lockly.controllerLayer.dtos.UserDto;
import com.example.lockly.domainLayer.User;
import com.example.lockly.mapper.UserMapper;
import com.example.lockly.serviceLayer.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto newUser){
        UserDto userResponse = UserMapper.forDto(service.register(UserMapper.forDomainFromDto(newUser)));
        return ResponseEntity
                .created(
                        UriComponentsBuilder
                                .newInstance()
                                .path("/users/add/{id}")
                                .buildAndExpand(userResponse.id())
                                .toUri()
                )
                .body(userResponse);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginUser) {
        service.login(loginUser.email(), loginUser.password());

        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/consult/id/{id}")
    public ResponseEntity<UserDto> consultById(@PathVariable Integer id){
        UserDto userResult = UserMapper.forDto(service.consultById(id));
        return ResponseEntity.ok(userResult);
    }

    @GetMapping(value = "/consult/email/{email}")
    public ResponseEntity<UserDto> consultByEmail(@PathVariable String email){
        UserDto userResult = UserMapper.forDto(service.consultByEmail(email));
        return ResponseEntity.ok(userResult);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Integer id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change/{id}")
    public ResponseEntity<UserDto> change(@RequestBody UserDto alterUser, @PathVariable Integer id) {
        UserDto userResult = UserMapper.forDto(service.change(UserMapper.forDomainFromDto(alterUser), id));

        return ResponseEntity.ok(userResult);
    }
}
