package com.vet.backend.dtos;

import com.vet.backend.models.AnimalType;
import jakarta.persistence.Entity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class AdoptionDto {
    private String name;
    private Long animalTypeId;
    private Integer age;
    private Long userId;
    private Long petId;
}
