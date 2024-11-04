package com.vet.backend.services;

import com.vet.backend.models.Adoption;

public interface IAdoptionService {
    public Adoption store(Adoption adoption);
}
