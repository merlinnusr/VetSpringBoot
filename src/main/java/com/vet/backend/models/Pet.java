package com.vet.backend.models;

import com.vet.backend.enums.AnimalType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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
    private String name;
    @ManyToOne
    @JoinColumn(name = "animal_type_id", nullable = false)
    private AnimalType type;
    @Min(value = 0, message = "La edad no puede ser menor a 0 años.")
    @Max(value = 99, message = "La edad no puede ser mayor a 99 años.")
    private Integer age;
    private Boolean availability;

}
