package sk.promark.petclinic.service;

import org.springframework.stereotype.Service;
import sk.promark.petclinic.entity.PetType;
import sk.promark.petclinic.exception.DomainException;
import sk.promark.petclinic.exception.UnsupportedPetTypeError;
import sk.promark.petclinic.repository.PetTypeRepository;

@Service
public class PetTypeService {
    private final PetTypeRepository repo;

    public PetTypeService(PetTypeRepository petTypeRepository) {
        this.repo = petTypeRepository;
    }

    public PetType lookupPetTypeByAnimalType(String animalType) {
        return repo.findByAnimalTypeIgnoreCase(animalType).orElseThrow(() -> new DomainException(new UnsupportedPetTypeError(animalType)));
    }

}
