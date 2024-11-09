package com.vet.backend.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vet.backend.models.Pet;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String petResponseJson;
    @Autowired
    private ObjectMapper objectMapper;

    String jsonPayload = """
            [
                {   
                    "id": 1,
                    "name": "panchito",
                    "animalType": {
                        "id": 1,
                        "name": "Mamal"
                    },
                    "age": 2,
                    "availability": true
                }
            ]
        """;
    @Test
    @Order(1)
    public void testPetCreate() throws Exception{
        String jsonPayload2 = """
                {   
                    "name": "panchito",
                    "animalTypeId": 1,
                    "age": 2,
                    "availability": true
                }
        """;
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/mascotas")
                        .content(jsonPayload2)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers
                        .content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();

        petResponseJson = result.getResponse().getContentAsString();

    }
    @Test
    @Order(2)
    public void testPetShowAll() throws Exception{
        Pet pet = objectMapper.readValue(petResponseJson, Pet.class);

        mockMvc.perform(get("/api/mascotas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value(pet.getId()))
                .andExpect(jsonPath("$[*].name").value(pet.getName()));

    }
}
