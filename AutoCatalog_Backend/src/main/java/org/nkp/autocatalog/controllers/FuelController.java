package org.nkp.autocatalog.controllers;

import jakarta.validation.Valid;
import org.nkp.autocatalog.models.fuels.FuelCreateModel;
import org.nkp.autocatalog.models.fuels.FuelModel;
import org.nkp.autocatalog.services.FuelService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fuel")
@PreAuthorize("hasAuthority('ADMIN')")
public class FuelController {
    private final FuelService service;

    public FuelController(FuelService service) {
        this.service = service;
    }

    @GetMapping("/fetch")
    public List<FuelModel> getAllFuels() {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createFuel(
            @Valid @RequestBody FuelCreateModel model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        return ResponseEntity.ok(service.create(model));
    }
}
