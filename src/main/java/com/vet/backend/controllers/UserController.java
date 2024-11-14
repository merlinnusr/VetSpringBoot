package com.vet.backend.controllers;

import com.vet.backend.dtos.LoginDto;
import com.vet.backend.dtos.UserDto;
import com.vet.backend.models.User;
import com.vet.backend.responses.AuthResponse;
import com.vet.backend.services.imp.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "Manejo de usuarios", description = "Operaciones de registro y login de usuarios")
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/login")
    @Operation(summary = "Login")
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
            log.error("e: ", e);
            throw new RuntimeException("Invalid email or password");
        }
    }
    @PostMapping("/register")
    public User register(@Valid @RequestBody UserDto userDto){
        return this.userService.save(userDto);
    }
    @GetMapping("/")
    public List<User> index(){
        return this.userService.findAllUsers();
    }
    @GetMapping("/{id}")
    public User show(@PathVariable Long id){
        return this.userService.find(id);
    }
    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @Valid @RequestBody UserDto userDto){
        return this.userService.updateUser(userDto, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> register(@PathVariable Long id){
        this.userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    

}
