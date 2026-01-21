package sk.promark.petclinic.model;

import java.util.List;

public record OwnersResponse(Metadata metadata, List<OwnerModel> data) {
}
