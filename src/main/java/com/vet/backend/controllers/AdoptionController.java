package com.vet.backend.controllers;

import com.vet.backend.dtos.AdoptionDto;
import com.vet.backend.models.Adoption;
import com.vet.backend.models.Pet;
import com.vet.backend.services.IAdoptionService;
import com.vet.backend.services.imp.AdoptionService;
import com.vet.backend.services.imp.AnimalTypeService;
import com.vet.backend.services.imp.PetService;
import com.vet.backend.services.imp.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/adopciones")
public class AdoptionController {

    //@Autowired
    //private PetService petService;
    @Autowired
    private IAdoptionService adoptionService;
    //@Autowired
    //private UserService userService;
    //@Autowired
    //private AnimalTypeService animalTypeService;
    @PostMapping("/crear")
    public Adoption create(@RequestBody AdoptionDto adoptionDto){
        System.out.println(adoptionDto);
//        var user = userService.find(adoptionDto.getUserId());
//        var animalType = animalTypeService.find(adoptionDto.getAnimalTypeId());
//        Pet pet = new Pet();
//        pet.setAge(adoptionDto.getAge());
//        pet.setName(adoptionDto.getName());
//        pet.setAnimalType(animalType);
//        pet.setAvailability(true);
//
//        Adoption adoption = new Adoption();
//        adoption.setName(adoptionDto.getName());
//        adoption.setUser(user);
//        adoption.setAdoptionDate(LocalDate.now());
//        adoption.setPet(pet);
//        log.warn("Warning");
//        this.petService.store(pet);
        return this.adoptionService.store(adoptionDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adoption> getAdoptionById(@PathVariable("id") Long id){
        Adoption adoption = this.adoptionService.findAdoptionById(id);
        return ResponseEntity.ok(adoption);
    }

    @GetMapping("/traer/todos")
    public List<Adoption> getAllAdoptions(){
        return this.adoptionService.getAdoptions();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteAdoptionById(@PathVariable("id") Long id){
        this.adoptionService.deleteAdoption(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<?> editAdoption(@PathVariable("id") Long id,
                                          @RequestBody AdoptionDto adoptionDto){
        Adoption adoption = this.adoptionService.updateAdoption(id, adoptionDto);
        return ResponseEntity.ok(adoption);
    }


}
