package sk.promark.petclinic.model;

import java.util.List;

public record Owner(String firstname, String lastname, String address, List<Pet> pets) {
}
