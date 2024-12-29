package org.nkp.autocatalog.controllers;

import jakarta.validation.Valid;
import org.nkp.autocatalog.models.fuels.FuelCreateModel;
import org.nkp.autocatalog.models.fuels.FuelModel;
import org.nkp.autocatalog.models.transmissions.TransmissionCreateModel;
import org.nkp.autocatalog.models.transmissions.TransmissionModel;
import org.nkp.autocatalog.services.FuelService;
import org.nkp.autocatalog.services.TransmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transmission")
@PreAuthorize("hasAuthority('ADMIN')")
public class TransmissionController {
    private final TransmissionService service;

    public TransmissionController(TransmissionService service) {
        this.service = service;
    }

    @GetMapping("/fetch")
    public List<TransmissionModel> getAllTransmissions() {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTransmission(
            @Valid @RequestBody TransmissionCreateModel model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        if (service.existsByName(model.getName())) {
            return ResponseEntity.ok(String.format("%s was already added", model.getName()));
        }

        return ResponseEntity.ok(service.create(model));
    }
}
