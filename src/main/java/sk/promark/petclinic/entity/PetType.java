package sk.promark.petclinic.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "pet_types")
public class PetType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "animal_type")
    private String animalType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PetType petType)) return false;
        return Objects.equals(animalType, petType.animalType);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(animalType);
    }
}
