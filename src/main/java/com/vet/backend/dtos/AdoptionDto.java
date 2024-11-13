package com.vet.backend.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
public class AdoptionDto {
    @NotNull(message = "User ID is required")
    @Positive(message = "User ID should be a positive number")
    private Long userId;
    @NotNull(message = "Pet ID is required")
    @Positive(message = "Pet ID should be a positive number")
    private Long petId;
    @Pattern(
            regexp = "\\d{4}-\\d{2}-\\d{2}",
            message = "Date must be in yyyy-MM-dd format"
    )
    private String adoptionDate;
    @PastOrPresent(message = "Adoption date cannot be in the future")
    public LocalDate getAdoptionDate() {
        return adoptionDate == null ? null : LocalDate.parse(adoptionDate);
    }
}
