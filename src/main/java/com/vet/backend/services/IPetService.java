package com.vet.backend.services;

import com.vet.backend.dtos.PetDto;
import com.vet.backend.models.Pet;

import java.util.List;

public interface IPetService {
    //create pet
    public Pet store(PetDto petDto);
    //update pet
    public Pet update(PetDto petDto, Long id);
    //get all pets
    public List<Pet> all();
    //find pet by id
    public Pet getPetById(Long id);

}
