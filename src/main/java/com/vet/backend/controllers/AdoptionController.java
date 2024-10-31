package com.vet.backend.controllers;

import com.vet.backend.dtos.AdoptionDto;
import com.vet.backend.models.Adoption;
import com.vet.backend.models.Pet;
import com.vet.backend.services.AdoptionService;
import com.vet.backend.services.AnimalTypeService;
import com.vet.backend.services.PetService;
import com.vet.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

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
    public Adoption create(@RequestBody AdoptionDto adoptionDto){
        System.out.println(adoptionDto);
        var user = userService.find(adoptionDto.getUserId());
        var animalType = animalTypeService.find(adoptionDto.getAnimalTypeId());
        Pet pet = new Pet();
        pet.setAge(adoptionDto.getAge());
        pet.setName(adoptionDto.getName());
        pet.setAnimalTypeId(animalType);
        pet.setAvailability(true);
        
        Adoption adoption = new Adoption();
        adoption.setName(adoptionDto.getName());
        adoption.setUser(user);
        adoption.setAdoptionDate(LocalDate.now());
        adoption.setPet(pet);

        this.petService.store(pet);
        return this.adoptionService.store(adoption);
    }
}
