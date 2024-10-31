package com.vet.backend.services;

import com.vet.backend.models.User;
import com.vet.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    public UserRepository userRepository;
    public User find(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe ese usuario"));
    }
}
