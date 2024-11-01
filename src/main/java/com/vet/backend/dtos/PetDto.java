package com.vet.backend.dtos;

import com.vet.backend.models.AnimalType;
import lombok.Data;

@Data
public class PetDto {
    private String name;
    private Long animalTypeId;
    private Integer age;
    private Boolean availability;
}
