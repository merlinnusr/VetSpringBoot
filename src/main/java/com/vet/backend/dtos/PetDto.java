package com.vet.backend.dtos;

import com.vet.backend.models.AnimalType;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PetDto {
    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;
    @NotNull(message = "Animal type ID is required")
    @Positive(message = "Animal type ID should be a positive number")
    private Long animalTypeId;
    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age should be at least 0")
    @Max(value = 99, message = "Age should not exceed 50")
    private Integer age;
    @NotNull(message = "Availability is required")
    private Boolean availability;
}
