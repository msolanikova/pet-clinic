package sk.promark.petclinic.service;

import org.springframework.stereotype.Service;
import sk.promark.petclinic.entity.PetType;
import sk.promark.petclinic.repository.PetTypeRepository;

import java.util.List;

@Service
public class PetTypeService {
    private final PetTypeRepository repo;

    public PetTypeService(PetTypeRepository petTypeRepository) {
        this.repo = petTypeRepository;
    }

    public PetType lookupPetTypeByAnimalType(String animalType) {
        List<PetType> types = repo.searchDistinctByAnimalType(animalType);

        if (types == null || types.isEmpty()) {
            throw new RuntimeException("Animal type not found");
        }

        return types.getFirst();
    }

}
