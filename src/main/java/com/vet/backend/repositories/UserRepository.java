package com.vet.backend.repositories;

import com.vet.backend.models.Adoption;
import com.vet.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);  // Find user by email
}
