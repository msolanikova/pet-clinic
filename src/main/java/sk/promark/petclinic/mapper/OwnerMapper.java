package sk.promark.petclinic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import sk.promark.petclinic.entity.Owner;
import sk.promark.petclinic.model.Metadata;
import sk.promark.petclinic.model.OwnerModel;
import sk.promark.petclinic.model.OwnersResponse;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, uses = PetMapper.class)
public interface OwnerMapper {

    OwnerModel toModel(Owner entity);

    List<OwnerModel> toModels(List<Owner> entities);

    @Mapping(target = "metadata", source = "metadata")
    @Mapping(target = "data", source = "owners")
    OwnersResponse toResponse(Metadata metadata, List<Owner> owners);

    @Mapping(target = "pets", ignore = true)
    Owner toEntity(OwnerModel ownerModel);

}
