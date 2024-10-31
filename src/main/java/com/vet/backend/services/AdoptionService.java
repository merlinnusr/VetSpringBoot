package com.vet.backend.services;

import com.vet.backend.models.Adoption;
import com.vet.backend.repositories.AdoptionRepository;
import com.vet.backend.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptionService {
    @Autowired
    private AdoptionRepository adoptionRepository;
    public Adoption store(Adoption adoption){
        return this.adoptionRepository.save(adoption);
    }
}
