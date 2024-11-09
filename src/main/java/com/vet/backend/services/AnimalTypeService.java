package com.vet.backend.services;

import com.vet.backend.exceptions.NotFoundException;
<<<<<<< Updated upstream:src/main/java/com/vet/backend/services/AnimalTypeService.java
import com.vet.backend.models.Adoption;
=======
>>>>>>> Stashed changes:src/main/java/com/vet/backend/services/imp/AnimalTypeService.java
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
        return this.animalTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe el tipo de animal"));
    }
    public AnimalType findById(Long id){
        return this.animalTypeRepository.findById(id).orElseThrow(() ->  new NotFoundException("Adopcion no encontrada"));
    }
}
