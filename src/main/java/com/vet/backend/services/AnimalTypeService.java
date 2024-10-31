package com.vet.backend.services;

import com.vet.backend.models.Adoption;
import com.vet.backend.models.AnimalType;
import com.vet.backend.repositories.AdoptionRepository;
import com.vet.backend.repositories.AnimalTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalTypeService {
    @Autowired
    private AnimalTypeRepository animalTypeRepository;
    public AnimalType find(Long id){
        return this.animalTypeRepository.findById(id).orElseThrow(() -> new RuntimeException("No existe el tipo de animal"));
    }
}
