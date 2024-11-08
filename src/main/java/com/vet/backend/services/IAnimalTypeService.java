package com.vet.backend.services;

import com.vet.backend.models.AnimalType;

public interface IAnimalTypeService {

    //find animal type
    public AnimalType find(Long id);

}
