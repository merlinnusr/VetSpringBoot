package com.vet.backend.controllers;

import com.vet.backend.dtos.PetDto;
import com.vet.backend.models.Pet;
import com.vet.backend.services.imp.AnimalTypeService;
import com.vet.backend.services.imp.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private AnimalTypeService animalTypeService;
    @PostMapping("/crear")
    public Pet create(@RequestBody PetDto petDto ){
//        var animalType = this.animalTypeService.find(petDto.getAnimalTypeId());
//        var pet = new Pet();
//        pet.setName(petDto.getName());
//        pet.setAvailability(petDto.getAvailability());
//        pet.setAge(petDto.getAge());
//        pet.setAnimalType(animalType);
        return petService.store(petDto);
    }
    @GetMapping("")
    public List<Pet> index(){
        return petService.all();
    }
    @PutMapping("/{id}")
    public Pet update(@RequestBody PetDto petDto, @PathVariable Long id){
        return this.petService.update(petDto, id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> destroy(@PathVariable Long id){
         this.petService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
