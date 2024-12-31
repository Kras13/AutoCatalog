package org.nkp.autocatalog.controllers;

import jakarta.validation.Valid;
import org.nkp.autocatalog.models.brands.BrandCreateModel;
import org.nkp.autocatalog.models.brands.BrandModel;
import org.nkp.autocatalog.services.BrandService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/brand")
@PreAuthorize("hasAuthority('ADMIN')")
public class BrandController {
    private final BrandService service;

    public BrandController(BrandService service) {
        this.service = service;
    }

    @GetMapping("/fetch")
    public List<BrandModel> getAllBrands() {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBrand(
            @Valid @RequestBody BrandCreateModel model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        return ResponseEntity.ok(service.create(model));
    }
}
