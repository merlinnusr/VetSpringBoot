package com.vet.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50, message = "Name cannot be longer than 50 characters")
    @Column(length = 50)  // This sets the column length in the database
    private String name;
    @ManyToOne
    @JoinColumn(name = "animal_type_id", nullable = false)
    private AnimalType animalTypeId;
    @Min(value = 0, message = "La edad no puede ser menor a 0 años.")
    @Max(value = 99, message = "La edad no puede ser mayor a 99 años.")
    private Integer age;
    private Boolean availability;

}
