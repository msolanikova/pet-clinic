package sk.promark.petclinic.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sk.promark.petclinic.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, String> {
    boolean existsByFirstnameIgnoreCaseAndLastnameIgnoreCaseAndAddressIgnoreCase(String firstname, String lastname,
                                                                                 String address);

    Owner findByUuid(String uuid);

    Page<Owner> findByLastnameContainsIgnoreCase(Pageable pageable, String lastname);
}
