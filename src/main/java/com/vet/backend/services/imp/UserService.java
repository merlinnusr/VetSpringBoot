package com.vet.backend.services.imp;

import com.vet.backend.dtos.UserDto;
import com.vet.backend.exceptions.NotFoundException;
import com.vet.backend.models.User;
import com.vet.backend.repositories.RoleRepository;
import com.vet.backend.repositories.UserRepository;
import com.vet.backend.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    public UserRepository userRepository;
    @Autowired
    public RoleRepository roleRepository;
    public User find(Long id){
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe ese usuario"));
    }

    @Override
    public void deleteUser(Long id) {

    }

    @Override
    public User updateUser(UserDto userDto) {
        return null;
    }


    @Override
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    public User save(UserDto userDto){

        User user = new User();
        user.setName(userDto.getName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        var role = roleRepository.findByName("ROLE_USER").orElseThrow(() -> new RuntimeException("No se encontro el rol"));
        user.setRole(role);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return user;
    }
}
