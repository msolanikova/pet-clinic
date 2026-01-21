package sk.promark.petclinic.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record OwnerModel(@NotBlank String firstname, @NotBlank String lastname, @NotBlank String address,
                         @Valid @NotEmpty List<PetModel> pets) {
}
