package com.example.lockly.controllerLayer;

import com.example.lockly.controllerLayer.dtos.UserDto;
import com.example.lockly.serviceLayer.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody UserDto ){

        return ;
    }

    @GetMapping(value = "/consult/{id}")
    public ResponseEntity<UserDto> consultById(@PathVariable Long id){

        return ResponseEntity.ok();
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Long id){

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/change")
    public ResponseEntity<UserDto> change(@RequestBody ) {
        return ;
    }
}
