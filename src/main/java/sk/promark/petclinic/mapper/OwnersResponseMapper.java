package sk.promark.petclinic.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import sk.promark.petclinic.model.Metadata;
import sk.promark.petclinic.model.Owner;
import sk.promark.petclinic.model.OwnersResponse;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface OwnersResponseMapper {

    Owner toOwnerDto(sk.promark.petclinic.entity.Owner entity);

    List<Owner> toOwnerDtos(List<sk.promark.petclinic.entity.Owner> entities);

    @Mapping(target = "metadata", source = "metadata")
    @Mapping(target = "data", source = "owners")
    OwnersResponse toResponse(Metadata metadata, List<sk.promark.petclinic.entity.Owner> owners);

    @Mapping(target = "pets", ignore = true)
    sk.promark.petclinic.entity.Owner dtoToEntity(Owner owner);
}
