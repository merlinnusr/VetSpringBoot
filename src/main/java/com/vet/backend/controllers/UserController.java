package com.vet.backend.controllers;

import com.vet.backend.dtos.UserDto;
import com.vet.backend.models.User;
import com.vet.backend.services.imp.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    @Autowired
    private UserService userService;
//    public User login(){
//
//    }
    public User register(UserDto userDto){
        return this.userService.save(userDto);
    }
}
