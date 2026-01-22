package sk.promark.petclinic.exception;

import org.springframework.http.HttpStatus;

public record OwnerExistsError(String firstname, String lastname) implements DomainError {
    @Override
    public String message() {
        return String.format("Owner [%s %s] already exists", firstname, lastname);
    }

    @Override
    public int status() {
        return HttpStatus.CONFLICT.value();
    }

    @Override
    public String code() {
        return "OWNER_EXISTS";
    }

}
