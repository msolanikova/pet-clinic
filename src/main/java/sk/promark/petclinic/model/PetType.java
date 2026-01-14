package sk.promark.petclinic.model;

public class PetType {
    private String animalType;

    public static PetType toPetType(sk.promark.petclinic.entity.PetType petTypeEntity) {
        return new PetType(petTypeEntity.getAnimalType());
    }

    public PetType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }
}
