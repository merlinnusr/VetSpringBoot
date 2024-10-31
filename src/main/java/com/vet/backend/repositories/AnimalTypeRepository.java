package com.vet.backend.repositories;

import com.vet.backend.models.AnimalType;
import com.vet.backend.models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalTypeRepository extends JpaRepository<AnimalType, Long> {
}
