package sk.promark.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.promark.petclinic.entity.PetType;

import java.util.Optional;

public interface PetTypeRepository extends JpaRepository<PetType, Integer> {
    Optional<PetType> findByAnimalTypeIgnoreCase(String animalType);
}
