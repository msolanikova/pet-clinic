package sk.promark.petclinic.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record PetModel(@NotBlank String name, @NotBlank String animalType,
                       @NotNull @PastOrPresent LocalDate dateOfBirth) {
}
