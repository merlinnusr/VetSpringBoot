package com.vet.backend.controllers;

import com.vet.backend.dtos.AdoptionDto;
import com.vet.backend.models.Adoption;
import com.vet.backend.models.AnimalType;
import com.vet.backend.models.Pet;
import com.vet.backend.models.User;
import com.vet.backend.services.AdoptionService;
import com.vet.backend.services.AnimalTypeService;
import com.vet.backend.services.PetService;
import com.vet.backend.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class AdoptionControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PetService petService;

    @Mock
    private AdoptionService adoptionService;

    @Mock
    private UserService userService;

    @Mock
    private AnimalTypeService animalTypeService;

    @InjectMocks
    private AdoptionController adoptionController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(adoptionController).build();
    }
    @Test
    void createAdoptionTest() throws Exception {
        // Preparar DTO de adopción
        AdoptionDto adoptionDto = new AdoptionDto();
        adoptionDto.setUserId(1L);
        adoptionDto.setAnimalTypeId(1L);
        adoptionDto.setName("Firulais");
        adoptionDto.setAge(2);

        // Mockear los servicios y sus respuestas
        User user = new User();
        user.setId(1L);

        AnimalType animalType = new AnimalType();
        animalType.setId(1L);

        Pet pet = new Pet();
        pet.setName("Firulais");
        pet.setAge(2);
        pet.setAnimalType(animalType);

        Adoption adoption = new Adoption();
        adoption.setName("Firulais");
        adoption.setUser(user);
        adoption.setAdoptionDate(LocalDate.now());
        adoption.setPet(pet);

        when(userService.find(1L)).thenReturn(user);
        when(animalTypeService.find(1L)).thenReturn(animalType);
        when(petService.store(any(Pet.class))).thenReturn(pet);
        when(adoptionService.store(any(Adoption.class))).thenReturn(adoption);

//        // Realizar solicitud de prueba
//        mockMvc.perform(post("/api/adopciones")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content("""
//                        {
//                            "userId": 1,
//                            "animalTypeId": 1,
//                            "name": "Firulais",
//                            "age": 2
//                        }
//                        """))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.name", is("Firulais")))
//                .andExpect(jsonPath("$.user.id", is(1)))
//                .andExpect(jsonPath("$.pet.name", is("Firulais")));
    }
}
