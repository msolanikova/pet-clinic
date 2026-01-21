package sk.promark.petclinic.mapper;

import org.mapstruct.*;
import sk.promark.petclinic.entity.Pet;
import sk.promark.petclinic.entity.PetType;
import sk.promark.petclinic.model.PetModel;
import sk.promark.petclinic.service.PetTypeService;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {

    @Mapping(target = "animalType", source = "petType.animalType")
    PetModel toModel(Pet entity);

    List<PetModel> toModels(List<Pet> entities);

    @Mapping(target = "petType", source = "animalType", qualifiedByName = "animalTypeToPetType")
    @Mapping(target = "owner", ignore = true)
    Pet toEntity(PetModel model, @Context PetTypeService petTypeService);

    @Named("animalTypeToPetType")
    default PetType animalTypeToPetType(String animalType, @Context PetTypeService petTypeService) {
        return petTypeService.lookupPetTypeByAnimalType(animalType);
    }
}
