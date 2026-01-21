package sk.promark.petclinic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sk.promark.petclinic.entity.Owner;
import sk.promark.petclinic.entity.Pet;
import sk.promark.petclinic.mapper.OwnersResponseMapper;
import sk.promark.petclinic.mapper.PetMapper;
import sk.promark.petclinic.model.Metadata;
import sk.promark.petclinic.model.OwnersResponse;
import sk.promark.petclinic.repository.OwnerRepository;

import java.util.List;


@Service
public class OwnerService {
    private static final Logger LOG = LoggerFactory.getLogger(OwnerService.class);

    private final OwnerRepository repo;
    private final OwnersResponseMapper mapper;
    private final PetMapper petMapper;
    private final PetTypeService petTypeService;

    public OwnerService(OwnerRepository ownerRepository, OwnersResponseMapper mapper, PetMapper petMapper,
                        PetTypeService petTypeService) {
        this.repo = ownerRepository;
        this.mapper = mapper;
        this.petMapper = petMapper;
        this.petTypeService = petTypeService;
    }

    public OwnersResponse getOwners(int page, int size) {
        Page<Owner> owners = repo.findAll(PageRequest.of(page, size, Sort.by("lastname")));
        Metadata metadata = new Metadata(owners.getTotalElements(), page, size);

        return mapper.toResponse(metadata, owners.getContent());
    }

    public sk.promark.petclinic.model.Owner createOwner(sk.promark.petclinic.model.Owner owner) {
        Owner entity = mapper.dtoToEntity(owner);

        if (owner.pets() != null) {
            List<Pet> pets = owner.pets().stream().map(p -> petMapper.toPetEntity(p, petTypeService)).toList();

            pets.forEach(p -> p.setOwner(entity));
            entity.setPets(pets);
        }

        Owner savedEntity = repo.save(entity);

        sk.promark.petclinic.model.Owner ownerModel = mapper.toOwnerDto(savedEntity);
        LOG.info("Created owner {}", ownerModel);
        return ownerModel;
    }
}
