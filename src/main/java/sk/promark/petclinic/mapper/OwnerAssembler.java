package sk.promark.petclinic.mapper;

import org.springframework.stereotype.Component;
import sk.promark.petclinic.entity.Owner;
import sk.promark.petclinic.entity.Pet;
import sk.promark.petclinic.model.OwnerModel;
import sk.promark.petclinic.service.PetTypeService;

import java.util.List;

@Component
public class OwnerAssembler {

    private final OwnerMapper ownerMapper;
    private final PetMapper petMapper;
    private final PetTypeService petTypeService;

    public OwnerAssembler(OwnerMapper ownerMapper, PetMapper petMapper, PetTypeService petTypeService) {
        this.ownerMapper = ownerMapper;
        this.petMapper = petMapper;
        this.petTypeService = petTypeService;
    }

    public Owner toEntity(OwnerModel model) {
        Owner entity = ownerMapper.toEntity(model);

        if (model.pets() != null) {
            List<Pet> pets = model.pets().stream().map(p -> petMapper.toEntity(p, petTypeService)).toList();

            pets.forEach(p -> p.setOwner(entity));
            entity.setPets(pets);
        }

        return entity;
    }

}
