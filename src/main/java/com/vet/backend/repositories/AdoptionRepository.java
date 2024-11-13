package com.vet.backend.repositories;

import com.vet.backend.models.Adoption;
import com.vet.backend.models.Pet;
import com.vet.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdoptionRepository extends JpaRepository<Adoption, Long> {
    Optional<Adoption> findByUserId(Long userId);
    Optional<Adoption> findByPetId(Long petId);

}
