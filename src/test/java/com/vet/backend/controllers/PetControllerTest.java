package com.vet.backend.controllers;

import com.vet.backend.services.PetService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PetControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private String petResponseJson;
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
    public void testPetShowAll() throws Exception{
        mockMvc.perform(get("/api/mascotas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*].id").value("panchito"));
    }
}
