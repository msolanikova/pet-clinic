package sk.promark.petclinic.controller;

import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.promark.petclinic.model.OwnersResponse;
import sk.promark.petclinic.service.OwnerService;

@RequestMapping("/owners")
@RestController
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public OwnersResponse getOwners(@RequestParam(name = "page", defaultValue = "0") @Min(0) int page,
                                    @RequestParam(name = "size", defaultValue = "2") @Min(1) int size) {

        return ownerService.getOwners(page, size);
    }
}
