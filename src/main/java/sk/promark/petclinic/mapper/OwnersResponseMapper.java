package sk.promark.petclinic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import sk.promark.petclinic.model.Metadata;
import sk.promark.petclinic.model.Owner;
import sk.promark.petclinic.model.OwnersResponse;
import sk.promark.petclinic.model.Pet;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnersResponseMapper {

    // ---- Nested mappings ----
    Pet toPetDto(sk.promark.petclinic.entity.Pet entity);

    List<Pet> toPetDtos(List<sk.promark.petclinic.entity.Pet> entities);

    Owner toOwnerDto(sk.promark.petclinic.entity.Owner entity);

    List<Owner> toOwnerDtos(List<sk.promark.petclinic.entity.Owner> entities);

    @Mapping(target = "metadata", source = "metadata")
    @Mapping(target = "data", source = "owners")
    OwnersResponse toResponse(Metadata metadata, List<sk.promark.petclinic.entity.Owner> owners);
}
