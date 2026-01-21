package sk.promark.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.promark.petclinic.entity.PetType;

import java.util.List;

public interface PetTypeRepository extends JpaRepository<PetType, Integer> {
    List<PetType> searchAllByAnimalType(String animalType);

    List<PetType> searchDistinctByAnimalType(String animalType);
}
