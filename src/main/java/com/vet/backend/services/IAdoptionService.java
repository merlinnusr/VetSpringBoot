package com.vet.backend.services;

import com.vet.backend.dtos.AdoptionDto;
import com.vet.backend.models.Adoption;

import java.util.List;

public interface IAdoptionService {
    //create adoption
    public Adoption store(AdoptionDto adoptionDto);
    //delete adoption
    public void deleteAdoption(Long id);
    //get all adoptions
    public List<Adoption> getAdoptions();
    //find adoption by id
    public Adoption findAdoptionById(Long id);
    //update Adoption
    public Adoption updateAdoption(Long id, AdoptionDto adoptionDto);

}
