package com.vet.backend.services;

import com.vet.backend.dtos.PetDto;
import com.vet.backend.models.Pet;

public interface IPetService {
    //create pet
    public Pet store(Pet pet);
    //update pet
    public Pet update(PetDto petDto, Long id);

}
