package com.vet.backend.controllers;

import com.vet.backend.dtos.AdoptionDto;
import com.vet.backend.models.Adoption;
import com.vet.backend.models.AnimalType;
import com.vet.backend.models.Pet;
import com.vet.backend.models.User;

import com.vet.backend.services.imp.AdoptionService;
import com.vet.backend.services.imp.AnimalTypeService;
import com.vet.backend.services.imp.PetService;
import com.vet.backend.services.imp.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles("test")
public class AdoptionControllerTest {
    private MockMvc mockMvc;

    @Mock
    private PetService petService;

    @Mock
    private com.vet.backend.services.imp.AdoptionService adoptionService;

    @Mock
    private com.vet.backend.services.imp.UserService userService;

    @Mock
    private com.vet.backend.services.imp.AnimalTypeService animalTypeService;

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
        adoption.setUser(user);
        adoption.setAdoptionDate(LocalDate.now());
        adoption.setPet(pet);

        when(userService.find(1L)).thenReturn(user);
        when(animalTypeService.find(1L)).thenReturn(animalType);
//        when(petService.store(any(Pet.class))).thenReturn(pet);
//        when(adoptionService.store(any(Adoption.class))).thenReturn(adoption);

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

    @Test
    void getAdoptionByIdTest() throws Exception{
        User user = new User();
        user.setId(1L);

        Pet pet = new Pet();
        pet.setId(1L);
        pet.setName("Firulais");

        Adoption adoption = new Adoption();
        adoption.setId(1L);
        adoption.setUser(user);
        adoption.setPet(pet);
        adoption.setAdoptionDate(LocalDate.now());

        when(adoptionService.findAdoptionById(1L)).thenReturn(adoption);

        mockMvc.perform(get("/api/adopciones/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.user.id").value(1));
    }

    @Test
    void getAllAdoptionsTest() throws Exception {
        User user1 = new User();
        user1.setId(1L);
        User user2 = new User();
        user2.setId(2L);

        Pet pet1 = new Pet();
        pet1.setId(1L);
        pet1.setName("Firulais");

        Pet pet2 = new Pet();
        pet2.setId(2L);
        pet2.setName("Lassie");

        Adoption adoption1 = new Adoption();
        adoption1.setId(1L);
        adoption1.setUser(user1);
        adoption1.setPet(pet1);
        adoption1.setAdoptionDate(LocalDate.now());

        Adoption adoption2 = new Adoption();
        adoption2.setId(2L);
        adoption2.setUser(user2);
        adoption2.setPet(pet2);
        adoption2.setAdoptionDate(LocalDate.now());

        List<Adoption> adoptions = Arrays.asList(adoption1, adoption2);

        when(adoptionService.getAdoptions()).thenReturn(adoptions);

        mockMvc.perform(get("/api/adopciones/traer/todos")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[1].id").value(2));
    }

    @Test
    void deleteAdoptionByIdTest() throws Exception {
        mockMvc.perform(delete("/api/adopciones/eliminar/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    void editAdoptionTest() throws Exception {
        AdoptionDto adoptionDto = new AdoptionDto();
        adoptionDto.setUserId(1L);
        adoptionDto.setPetId(2L);

        User user = new User();
        user.setId(1L);

        Pet pet = new Pet();
        pet.setId(2L);
        pet.setName("Lassie");

        Adoption adoption = new Adoption();
        adoption.setId(1L);
        adoption.setUser(user);
        adoption.setPet(pet);
        adoption.setAdoptionDate(LocalDate.now());

        when(adoptionService.updateAdoption(1L, adoptionDto)).thenReturn(adoption);

        mockMvc.perform(put("/api/adopciones/editar/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "userId": 1,
                                    "petId": 2,
                                    "name": "Adopción Editada"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.pet.name").value("Lassie"))
                .andExpect(jsonPath("$.user.id").value(1));
    }

}
