package sk.promark.petclinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sk.promark.petclinic.entity.Owner;

public interface OwnerRepository extends JpaRepository<Owner, String> {
}
