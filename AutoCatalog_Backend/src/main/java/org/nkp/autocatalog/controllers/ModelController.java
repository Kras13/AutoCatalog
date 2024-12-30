package org.nkp.autocatalog.controllers;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.nkp.autocatalog.models.Models.ModelCreateRequest;
import org.nkp.autocatalog.services.ModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/model")
public class ModelController {
    private final ModelService service;

    public ModelController(ModelService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getForMake(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.getForMake(id));
        }
        catch(EntityNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(
            @Valid @RequestBody ModelCreateRequest model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        return ResponseEntity.ok(service.create(model));
    }
}
