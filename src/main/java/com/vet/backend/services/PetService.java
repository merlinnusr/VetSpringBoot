package com.vet.backend.services;

import com.vet.backend.dtos.PetDto;
import com.vet.backend.models.Pet;
import com.vet.backend.repositories.AnimalTypeRepository;
import com.vet.backend.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private AnimalTypeRepository animalTypeRepository;
    public Pet store(Pet pet){
        return this.petRepository.save(pet);
    }
    public Pet update(PetDto petDto, Long id){
        var pet = this.petRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro la mascota con el id : " + id));
        var animalType = this.animalTypeRepository.findByUserId(id).orElseThrow(() -> new RuntimeException("No se encontro tipo de animal con el id:  " + id));

        pet.setName(petDto.getName());
        pet.setAge(petDto.getAge());
        pet.setAvailability(pet.getAvailability());
        pet.setAnimalTypeId(animalType);
        return pet;
    }
    public void delete(Long id){
        this.petRepository.deleteById(id);
    }
    public List<Pet> all() {
        return this.petRepository.findAll();
    }
}
