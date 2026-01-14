package sk.promark.petclinic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import sk.promark.petclinic.model.OwnersResponse;
import sk.promark.petclinic.service.OwnerService;

@RestController
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping("/owners")
    public OwnersResponse getOwners(@RequestParam(name = "page", required = false) Integer page, @RequestParam(name = "size", required = false) Integer size) {
        page = page == null ? 0 : page;
        size = size == null ? 2 : size;

        return ownerService.getOwners(page, size);
    }
}
