package sk.promark.petclinic.exception;

public record UnsupportedPetTypeError(String animalType) implements DomainError {
    @Override
    public String message() {
        return "Animal type [" + animalType + "] is not supported";
    }

    @Override
    public String code() {
        return "UNSUPPORTED_PET_TYPE";
    }


}
