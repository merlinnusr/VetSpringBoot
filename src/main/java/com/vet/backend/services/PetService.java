package com.vet.backend.services;

import com.vet.backend.models.Pet;
import com.vet.backend.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PetService {
    @Autowired
    private PetRepository petRepository;
    public Pet store(Pet pet){
        return this.petRepository.save(pet);
    }
}
