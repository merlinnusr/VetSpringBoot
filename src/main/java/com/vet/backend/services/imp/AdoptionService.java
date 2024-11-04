package com.vet.backend.services.imp;

import com.vet.backend.dtos.AdoptionDto;
import com.vet.backend.dtos.PetDto;
import com.vet.backend.models.Adoption;
import com.vet.backend.models.Pet;
import com.vet.backend.repositories.AdoptionRepository;
import com.vet.backend.services.IAdoptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
    private AnimalTypeService animalTypeService;

    public Adoption store(AdoptionDto adoptionDto){
        var user = userService.find(adoptionDto.getUserId());
        var animalType = animalTypeService.find(adoptionDto.getAnimalTypeId());
        PetDto petDto = new PetDto();
        petDto.setAge(adoptionDto.getAge());
        petDto.setName(adoptionDto.getName());
        petDto.setAnimalTypeId(animalType.getId());
        petDto.setAvailability(true);
        Pet pet = this.petService.store(petDto);

        Adoption adoption = new Adoption();
        adoption.setName(adoptionDto.getName());
        adoption.setUser(user);
        adoption.setAdoptionDate(LocalDate.now());
        adoption.setPet(pet);

        log.warn("Warning");
        // this.petService.store(pet);
        return this.adoptionRepository.save(adoption);
    }

}
