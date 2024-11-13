package com.vet.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.backend.models.Pet;
import com.vet.backend.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")  // Activates the test profile
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String petResponseJson;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private UserRepository userRepository;

    @Test
    @WithMockUser(username = "john.doe@example.com", password = "password", roles = "USER")
    public void testPetCreate() throws Exception{
        String jsonPayload2 = """
                {   
                    "name": "cheems",
                    "animalTypeId": 1,
                    "age": 2,
                    "availability": true
                }
        """;
            mockMvc.perform(MockMvcRequestBuilders.post("/api/mascotas/crear")
                        .content(jsonPayload2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.id").value(2))
                    .andExpect(jsonPath("$.name").value("cheems"));


    }

    @Test
    @WithMockUser(username = "john.doe@example.com", password = "password", roles = "USER")
    public void testPetShowAll() throws Exception{
        mockMvc.perform(get("/api/mascotas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(1))
                .andExpect(jsonPath("$[*].name").value("Panchito"));
    }

    @Test
    @WithMockUser(username = "john.doe@example.com", password = "password", roles = "USER")
    public void testPetUpdate() throws Exception{
        String updateJsonPayload = """
                {   
                    "name": "Panchito",
                    "animalTypeId": 2,
                    "age": 3,
                    "availability": false
                }
        """;
        mockMvc.perform(put("/api/mascotas/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateJsonPayload)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.age").value("3"))
                .andExpect(jsonPath("$.animalType.id").value("2"))
                .andExpect(jsonPath("$.name").value("Panchito"));


    }

    @Test
    @WithMockUser(username = "john.doe@example.com", password = "password", roles = "USER")
    public void testPetDelete() throws Exception{
        mockMvc.perform(delete("/api/mascotas/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNoContent());

    }
}
