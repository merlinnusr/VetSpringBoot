package com.vet.backend.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@Table(name = "animal_types")
public class AnimalType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(max = 50, message = "Name cannot be longer than 50 characters")
    @Column(length = 50)  // This sets the column length in the database
    private String name;
}
