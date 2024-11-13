package com.vet.backend.seeders;

import com.vet.backend.models.AnimalType;
import com.vet.backend.models.Pet;
import com.vet.backend.models.User;
import com.vet.backend.repositories.AnimalTypeRepository;
import com.vet.backend.repositories.PetRepository;
import com.vet.backend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
@Configuration
public class Seeder {
    @Bean
    @Transactional
    public CommandLineRunner seedDatabase(
            UserRepository userRepository,
            PetRepository petRepository,
            AnimalTypeRepository animalTypeRepository
    ) {
        return args -> {
            // Check if data already exists to prevent duplicate seeding
            if (userRepository.count() == 0) {
                // Create Users
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                User user1 = new User();
                user1.setEmail("john.doe@example.com");
                user1.setName("John");
                user1.setPhone("3314135849");
                user1.setPassword(passwordEncoder.encode("password"));
                userRepository.save(user1);



                createAndSaveAnimalType("Mammal", animalTypeRepository);
                createAndSaveAnimalType("Bird", animalTypeRepository);
                createAndSaveAnimalType("Reptile", animalTypeRepository);
                createAndSaveAnimalType("Fish", animalTypeRepository);
                createAndSaveAnimalType("Amphibian", animalTypeRepository);
                Pet pet = new Pet();
                pet.setAge(1);
                var animalType = animalTypeRepository.findById(1L).orElseThrow(() -> new RuntimeException("Error"));
                pet.setAnimalType(animalType);
                pet.setName("Panchito");
                pet.setAvailability(true);
                petRepository.save(pet);
                System.out.println("Database seeded successfully!");
            } else {
                System.out.println("Database already contains data. Seeding skipped.");
            }
        };
    }

    private void createAndSaveAnimalType(String name, AnimalTypeRepository animalTypeRepository) {
        var animalType = new com.vet.backend.models.AnimalType();
        animalType.setName(name);
        animalTypeRepository.save(animalType);
    }
}
