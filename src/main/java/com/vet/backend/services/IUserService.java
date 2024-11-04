package com.vet.backend.services;

import com.vet.backend.dtos.UserDto;
import com.vet.backend.models.User;

public interface IUserService {
    //create user
    public User save(UserDto userDto);
    //find user
    public User find(Long id);
}
