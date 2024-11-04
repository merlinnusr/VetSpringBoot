package com.vet.backend.services.imp;

import com.vet.backend.models.Adoption;
import com.vet.backend.repositories.AdoptionRepository;
import com.vet.backend.services.IAdoptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptionService implements IAdoptionService {
    @Autowired
    private AdoptionRepository adoptionRepository;
    public Adoption store(Adoption adoption){
        return this.adoptionRepository.save(adoption);
    }

}
