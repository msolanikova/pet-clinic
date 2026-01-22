package sk.promark.petclinic.exception;

public class DomainException extends RuntimeException {

    private final DomainError error;

    public DomainException(DomainError error) {
        super(error.message());
        this.error = error;
    }

    public DomainError getError() {
        return error;
    }
}
