package sk.promark.petclinic.exception;

import org.springframework.http.HttpStatus;

public sealed interface DomainError permits OwnerExistsError, UnsupportedPetTypeError, OwnerNotFoundError {

    String message();

    default int status() {
        return HttpStatus.BAD_REQUEST.value();
    }

    String code();
}
