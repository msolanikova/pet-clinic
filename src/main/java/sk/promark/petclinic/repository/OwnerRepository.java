package sk.promark.petclinic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import sk.promark.petclinic.entity.Owner;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Page<Owner> findAll(Pageable pageable);
}
