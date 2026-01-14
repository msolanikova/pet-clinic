package sk.promark.petclinic.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "pets")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;

    private String name;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pet_type_id")
    private PetType petType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_uuid")
    private Owner owner;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public PetType getPetType() {
        return petType;
    }

    public void setPetType(PetType petType) {
        this.petType = petType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pet pet)) return false;
        return Objects.equals(getName(), pet.getName()) && Objects.equals(getDateOfBirth(), pet.getDateOfBirth()) && Objects.equals(getPetType(), pet.getPetType()) && Objects.equals(getOwner(), pet.getOwner());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDateOfBirth(), getPetType(), getOwner());
    }
}
