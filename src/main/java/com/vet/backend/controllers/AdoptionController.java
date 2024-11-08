package com.vet.backend.controllers;

import com.vet.backend.dtos.AdoptionDto;
import com.vet.backend.models.Adoption;
import com.vet.backend.models.Pet;
import com.vet.backend.services.AdoptionService;
import com.vet.backend.services.AnimalTypeService;
import com.vet.backend.services.PetService;
import com.vet.backend.services.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.logging.Logger;
@Slf4j
@RestController
@RequestMapping("/api/adopciones")
public class AdoptionController {

    @Autowired
    private PetService petService;
    @Autowired
    private AdoptionService adoptionService;
    @Autowired
    private UserService userService;
    @Autowired
    private AnimalTypeService animalTypeService;
    @PostMapping
    public Adoption create(@Valid @RequestBody AdoptionDto adoptionDto){
        System.out.println(adoptionDto);
        var user = userService.find(adoptionDto.getUserId());
        var animalType = animalTypeService.find(adoptionDto.getAnimalTypeId());
        Pet pet = new Pet();
        pet.setAge(adoptionDto.getAge());
        pet.setName(adoptionDto.getName());
        pet.setAnimalType(animalType);
        pet.setAvailability(true);
        
        Adoption adoption = new Adoption();
        adoption.setName(adoptionDto.getName());
        adoption.setUser(user);
        adoption.setAdoptionDate(LocalDate.now());
        adoption.setPet(pet);
        log.warn("Warning");
        this.petService.store(pet);
        return this.adoptionService.store(adoption);
    }
}
