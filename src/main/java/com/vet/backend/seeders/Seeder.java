package com.vet.backend.seeders;

import com.vet.backend.models.User;
import com.vet.backend.repositories.AnimalTypeRepository;
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

                var animalType = new com.vet.backend.models.AnimalType();
                animalType.setName("Mamal");
                animalTypeRepository.save(animalType);
                var animalType2 = new com.vet.backend.models.AnimalType();
                animalType.setName("Bird");
                animalTypeRepository.save(animalType2);
                var animalType3 = new com.vet.backend.models.AnimalType();
                animalType.setName("Reptile");
                animalTypeRepository.save(animalType3);
                var animalType4 = new com.vet.backend.models.AnimalType();
                animalType.setName("Fish");
                animalTypeRepository.save(animalType4);
                var animalType5 = new com.vet.backend.models.AnimalType();
                animalType.setName("AMPHIBIAN");
                animalTypeRepository.save(animalType5);
                System.out.println("Database seeded successfully!");
            } else {
                System.out.println("Database already contains data. Seeding skipped.");
            }
        };
    }
}
