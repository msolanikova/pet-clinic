package sk.promark.petclinic.model;

import java.time.LocalDate;

public record Pet(String name, String animalType, LocalDate dateOfBirth) {
}
