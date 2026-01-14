package sk.promark.petclinic.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    private String firstname;
    private String lastname;
    private String address;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "owner")
    private List<Pet> pets;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Owner owner)) return false;
        return Objects.equals(getFirstname(), owner.getFirstname()) && Objects.equals(getLastname(), owner.getLastname()) && Objects.equals(getAddress(), owner.getAddress());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstname(), getLastname(), getAddress());
    }
}
