package sk.promark.petclinic.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "owners")
public class Owner {
    @Id
    @Column(name = "uuid", length = 36, nullable = false, updatable = false)
    private String uuid = UUID.randomUUID().toString();
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
        return Objects.equals(getUuid(), owner.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUuid());
    }

    @Override
    public String toString() {
        return "Owner{" + "uuid='" + uuid + '\'' + ", firstname='" + firstname + '\'' + ", lastname='" + lastname + '\'' + ", address='" + address + '\'' + ", pets=" + pets + '}';
    }
}
