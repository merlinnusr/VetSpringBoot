package com.vet.backend.services.imp;

import com.vet.backend.dtos.PetDto;
import com.vet.backend.models.Pet;
import com.vet.backend.repositories.AdoptionRepository;
import com.vet.backend.repositories.AnimalTypeRepository;
import com.vet.backend.repositories.PetRepository;
import com.vet.backend.services.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService implements IPetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private AnimalTypeRepository animalTypeRepository;
    public Pet store(Pet pet){
        return this.petRepository.save(pet);
    }
    public Pet update(PetDto petDto, Long id){
        var pet = this.petRepository.findById(id).orElseThrow(() -> new RuntimeException("No se encontro la mascota con el id : " + id));
        var animalType = this.animalTypeRepository.findById(pet.getAnimalType().getId()).orElseThrow(() -> new RuntimeException("No se encontro la mascota con el id : " + id));
        pet.setName(petDto.getName());
        pet.setAge(petDto.getAge());
        pet.setAvailability(petDto.getAvailability());
        pet.setAnimalType(animalType);
        return pet;
    }
    public void delete(Long id){
        this.petRepository.deleteById(id);
    }
    public List<Pet> all() {
        return this.petRepository.findAll();
    }
}
