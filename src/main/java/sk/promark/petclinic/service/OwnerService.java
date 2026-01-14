package sk.promark.petclinic.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sk.promark.petclinic.entity.Owner;
import sk.promark.petclinic.mapper.OwnersResponseMapper;
import sk.promark.petclinic.model.Metadata;
import sk.promark.petclinic.model.OwnersResponse;
import sk.promark.petclinic.repository.OwnerRepository;

@Service
public class OwnerService {

    private final OwnerRepository repo;
    private final OwnersResponseMapper mapper;

    public OwnerService(OwnerRepository ownerRepository, OwnersResponseMapper mapper) {
        this.repo = ownerRepository;
        this.mapper = mapper;
    }

    public OwnersResponse getOwners(int page, int size) {
        Page<Owner> owners = repo.findAll(PageRequest.of(page, size, Sort.by("lastname")));
        Metadata metadata = new Metadata(owners.getTotalElements(), page, size);

        return mapper.toResponse(metadata, owners.getContent());
    }
}
