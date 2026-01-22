package sk.promark.petclinic.exception;

import org.springframework.http.HttpStatus;

public record OwnerNotFoundError(String uuid) implements DomainError {

    @Override
    public String message() {
        return "Owner with uuid [" + uuid + "] was not found";
    }

    @Override
    public int status() {
        return HttpStatus.NOT_FOUND.value();
    }

    @Override
    public String code() {
        return "OWNER_NOT_FOUND";
    }
}
