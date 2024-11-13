package com.vet.backend.services;

import com.vet.backend.dtos.UserDto;
import com.vet.backend.models.User;

import java.util.List;

public interface IUserService {
    //create user
    public User save(UserDto userDto);
    //find user by id
    public User find(Long id);
    //delete user
    public void deleteUser(Long id);
    //update user
    public User updateUser(UserDto userDto, Long id);
    //get all users
    public List<User> findAllUsers();
}
