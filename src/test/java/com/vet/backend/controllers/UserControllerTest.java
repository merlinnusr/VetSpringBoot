package com.vet.backend.controllers;

import com.vet.backend.dtos.LoginDto;
import com.vet.backend.dtos.UserDto;
import com.vet.backend.models.User;
import com.vet.backend.responses.AuthResponse;
import com.vet.backend.services.imp.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.http.MediaType;

@SpringBootTest
@ActiveProfiles("test")
public class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void loginTest_success() throws Exception {
        LoginDto loginDto = new LoginDto();
        loginDto.setEmail("test@example.com");
        loginDto.setPassword("password");

        Authentication authentication = mock(Authentication.class);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(authentication);

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "email": "test@example.com",
                    "password": "password"
                }
                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Login successful"))
                .andExpect(jsonPath("$.email").value("test@example.com"))
                .andExpect(jsonPath("$.role").value("ROLE_USER"));
    }

    @Test
    void loginTest_failure() throws Exception {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new RuntimeException("Invalid email or password"));

        mockMvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                {
                    "email": "wrong@example.com",
                    "password": "wrongpassword"
                }
                """))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void registerTest() throws Exception {
        // Simulación del DTO de registro
        UserDto userDto = new UserDto();
        userDto.setEmail("newuser@example.com");
        userDto.setPassword("newpassword");

        // Simular la respuesta del servicio de usuario
        User user = new User();
        user.setEmail("newuser@example.com");

        when(userService.save(any(UserDto.class))).thenReturn(user);

        // Realizar la solicitud de registro
        mockMvc.perform(post("/api/usuarios/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
            {
                "email": "newuser@example.com",
                "password": "newpassword"
            }
            """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("newuser@example.com"));
    }

}
