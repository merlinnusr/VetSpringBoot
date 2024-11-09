package com.vet.backend.controllers;

import com.vet.backend.dtos.LoginDto;
import com.vet.backend.dtos.UserDto;
import com.vet.backend.models.User;
<<<<<<< Updated upstream
import com.vet.backend.repositories.UserRepository;
import com.vet.backend.services.UserService;
=======
import com.vet.backend.responses.AuthResponse;
import com.vet.backend.services.imp.UserService;
import jakarta.validation.Valid;
>>>>>>> Stashed changes
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.authentication.AuthenticationManager;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping
    public AuthResponse login(@Valid @RequestBody LoginDto loginDto){
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getEmail(),
                            loginDto.getPassword()
                    )
            );

            return new AuthResponse(
                    "Login successful",
                    loginDto.getEmail(),
                    "ROLE_USER"
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid email or password");
        }
    }
    public User register(UserDto userDto){
        return this.userService.save(userDto);
    }
}
