package com.vet.backend.services.imp;

import com.vet.backend.models.AnimalType;
import com.vet.backend.repositories.AnimalTypeRepository;
import com.vet.backend.services.IAnimalTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnimalTypeService implements IAnimalTypeService {
    @Autowired
    private AnimalTypeRepository animalTypeRepository;
    public AnimalType find(Long id){
        return this.animalTypeRepository.findById(id).orElseThrow(() -> new NotFoundException("No existe el tipo de animal"));
    }
//    public AnimalType findById(Long id){
//        return this.animalTypeRepository.findById(id).orElseThrow(() ->  new RuntimeException("Adopcion no encontrada"));
//    }
}
