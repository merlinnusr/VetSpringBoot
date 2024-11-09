package com.vet.backend.services.imp;

import com.vet.backend.dtos.PetDto;
import com.vet.backend.models.Pet;
import com.vet.backend.repositories.AdoptionRepository;
import com.vet.backend.repositories.AnimalTypeRepository;
import com.vet.backend.repositories.PetRepository;
import com.vet.backend.services.IAnimalTypeService;
import com.vet.backend.services.IPetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("petServiceImpl")  // Give a unique name here
public class PetService implements IPetService {
    @Autowired
    private PetRepository petRepository;
    //@Autowired
    //private AdoptionRepository adoptionRepository;
    //@Autowired
    //private AnimalTypeRepository animalTypeRepository;
    @Autowired
    private AnimalTypeService animalTypeService;

    public Pet store(PetDto petDto){
        var animalType = this.animalTypeService.find(petDto.getAnimalTypeId());
        if(animalType==null){
            throw new RuntimeException("Animal Type Not Found");
        }
        var pet = new Pet();
        pet.setName(petDto.getName());
        pet.setAvailability(petDto.getAvailability());
        pet.setAge(petDto.getAge());
        pet.setAnimalType(animalType);
        return this.petRepository.save(pet);
    }
    public Pet update(PetDto petDto, Long id){
        var pet = this.getPetById(id);
        //var animalType = this.animalTypeRepository.findById(pet.getAnimalType().getId()).orElseThrow(() -> new RuntimeException("No se encontro la mascota con el id : " + id));
        var animalType = this.animalTypeService.find(petDto.getAnimalTypeId());
        pet.setName(petDto.getName());
        pet.setAge(petDto.getAge());
        pet.setAvailability(petDto.getAvailability());
        pet.setAnimalType(animalType);
        return this.petRepository.save(pet);
    }
    public void delete(Long id){
        if(this.getPetById(id) == null){
            throw new RuntimeException("Mascota no encontrada");
        }
        this.petRepository.deleteById(id);
    }
    public List<Pet> all() {
        return this.petRepository.findAll();
    }
    @Override
    public Pet getPetById(Long id) {
        return this.petRepository.findById(id).orElseThrow(()
                -> new RuntimeException("No se encontro la mascota con el id : " + id));
    }
}
