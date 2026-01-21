package sk.promark.petclinic.model;

import java.util.List;

public record OwnerModel(String firstname, String lastname, String address, List<PetModel> pets) {
}
