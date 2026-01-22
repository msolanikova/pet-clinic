package sk.promark.petclinic.service;

import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sk.promark.petclinic.entity.Owner;
import sk.promark.petclinic.exception.DomainException;
import sk.promark.petclinic.exception.OwnerExistsError;
import sk.promark.petclinic.exception.OwnerNotFoundError;
import sk.promark.petclinic.mapper.OwnerAssembler;
import sk.promark.petclinic.mapper.OwnerMapper;
import sk.promark.petclinic.model.Metadata;
import sk.promark.petclinic.model.OwnerModel;
import sk.promark.petclinic.model.OwnersResponse;
import sk.promark.petclinic.repository.OwnerRepository;


@Service
public class OwnerService {
    private static final Logger LOG = LoggerFactory.getLogger(OwnerService.class);

    private final OwnerRepository repo;
    private final OwnerAssembler assembler;
    private final OwnerMapper mapper;

    public OwnerService(OwnerRepository ownerRepository, OwnerAssembler assembler, OwnerMapper mapper) {
        this.repo = ownerRepository;
        this.assembler = assembler;
        this.mapper = mapper;
    }

    @Transactional
    public OwnersResponse getOwners(int page, int size) {
        Page<Owner> owners = repo.findAll(PageRequest.of(page, size, Sort.by("lastname")));
        Metadata metadata = new Metadata(owners.getTotalElements(), page, size);

        return mapper.toResponse(metadata, owners.getContent());
    }

    @Transactional
    public OwnerModel createOwner(OwnerModel owner) {
        OwnerModel model = new OwnerModel(owner.firstname().trim(), owner.lastname().trim(), owner.address().trim(),
                owner.pets());

        boolean ownerExists =
                repo.existsByFirstnameIgnoreCaseAndLastnameIgnoreCaseAndAddressIgnoreCase(model.firstname(),
                        model.lastname(), model.address());

        if (ownerExists) {
            throw new DomainException(new OwnerExistsError(model.firstname(), model.lastname()));
        }

        Owner entity = assembler.toEntity(model);
        Owner savedEntity = repo.save(entity);

        OwnerModel ownerModel = mapper.toModel(savedEntity);
        LOG.debug("Created owner {}", ownerModel);
        return ownerModel;
    }

    @Transactional
    public OwnerModel getOwner(String uuid) {
        Owner entity = repo.findByUuid(uuid);
        if (entity == null) {
            throw new DomainException(new OwnerNotFoundError(uuid));
        }
        OwnerModel ownerModel = mapper.toModel(entity);
        LOG.debug("Retrieved owner {}", ownerModel);
        return ownerModel;
    }

    @Transactional
    public OwnersResponse searchOwenrs(String lastnamePart, int page, int size) {
        Page<Owner> owners = repo.findByLastnameContainsIgnoreCase(PageRequest.of(page, size, Sort.by("lastname")),
                lastnamePart);
        Metadata metadata = new Metadata(owners.getTotalElements(), page, size);

        return mapper.toResponse(metadata, owners.getContent());
    }
}
