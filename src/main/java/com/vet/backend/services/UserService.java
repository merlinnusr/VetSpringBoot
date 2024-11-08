package com.vet.backend.services;

import com.vet.backend.dtos.UserDto;
import com.vet.backend.exceptions.NotFoundException;
import com.vet.backend.models.User;
import com.vet.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    public User find(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe ese usuario"));
    }
    public User save(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }
}
