package com.vet.backend.services.imp;

import com.vet.backend.dtos.AdoptionDto;
import com.vet.backend.dtos.PetDto;
import com.vet.backend.models.Adoption;
import com.vet.backend.models.Pet;
import com.vet.backend.repositories.AdoptionRepository;
import com.vet.backend.services.IAdoptionService;
import com.vet.backend.services.IAnimalTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class AdoptionService implements IAdoptionService {
    @Autowired
    private AdoptionRepository adoptionRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PetService petService;
    @Autowired
    private IAnimalTypeService animalTypeService;

    public Adoption store(AdoptionDto adoptionDto){
        var user = userService.find(adoptionDto.getUserId());
        var pet = petService.getPetById(adoptionDto.getPetId());

        Adoption adoption = new Adoption();
        adoption.setUser(user);
        adoption.setPet(pet);
        adoption.setAdoptionDate(LocalDate.now());

        log.warn("Warning");
        return this.adoptionRepository.save(adoption);
    }

    @Override
    public void deleteAdoption(Long id) {
        this.findAdoptionById(id);
        this.adoptionRepository.deleteById(id);
    }

    @Override
    public List<Adoption> getAdoptions() {
        return this.adoptionRepository.findAll();
    }

    @Override
    public Adoption findAdoptionById(Long id) {
        return this.adoptionRepository.findById(id).orElse(null);
    }

    @Override
    public Adoption updateAdoption(Long id, AdoptionDto adoptionDto) {
        Adoption adoption = this.findAdoptionById(id);

        var user = userService.find(adoptionDto.getUserId());
        Pet pet = this.petService.getPetById(adoptionDto.getPetId());

        adoption.setUser(user);
        adoption.setAdoptionDate(adoption.getAdoptionDate());
        adoption.setPet(pet);

        return adoptionRepository.save(adoption);
    }

}