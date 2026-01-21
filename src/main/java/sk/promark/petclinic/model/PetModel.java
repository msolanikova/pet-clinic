package sk.promark.petclinic.model;

import java.time.LocalDate;

public record PetModel(String name, String animalType, LocalDate dateOfBirth) {
}
