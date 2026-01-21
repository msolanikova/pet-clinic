package sk.promark.petclinic.mapper;

import org.mapstruct.*;
import sk.promark.petclinic.entity.PetType;
import sk.promark.petclinic.model.Pet;
import sk.promark.petclinic.service.PetTypeService;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PetMapper {

    @Mapping(target = "animalType", source = "petType.animalType")
    Pet toPetDto(sk.promark.petclinic.entity.Pet entity);

    List<Pet> toPetDtos(List<sk.promark.petclinic.entity.Pet> entities);

    @Mapping(target = "petType", source = "animalType", qualifiedByName = "animalTypeToPetType")
    @Mapping(target = "owner", ignore = true)
    sk.promark.petclinic.entity.Pet toPetEntity(sk.promark.petclinic.model.Pet model,
                                                @Context PetTypeService petTypeService);

    @Named("animalTypeToPetType")
    default PetType animalTypeToPetType(String animalType, @Context PetTypeService petTypeService) {
        return petTypeService.lookupPetTypeByAnimalType(animalType);
    }
}
