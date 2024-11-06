package com.vet.backend.dtos;

import com.vet.backend.models.AnimalType;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AdoptionDto {
    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name should not exceed 50 characters")
    private String name;
    @NotNull(message = "Animal type ID is required")
    @Positive(message = "Animal type ID should be a positive number")
    private Long animalTypeId;
    @NotNull(message = "Age is required")
    @Min(value = 0, message = "Age should be at least 0")
    @Max(value = 99, message = "Age should not exceed 50")
    private Integer age;
    @NotNull(message = "User ID is required")
    @Positive(message = "User ID should be a positive number")
    private Long userId;

}
