package org.nkp.autocatalog.controllers;

import jakarta.validation.Valid;
import org.nkp.autocatalog.models.cars.*;
import org.nkp.autocatalog.services.CarService;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/fetch")
    public List<CarFetchResponse> getAllCars() {
        return service.getAll();
    }

    @GetMapping("/filter")
    public CarFilterResponse filterCars(
            CarFilterRequest request,
            @RequestParam(defaultValue = "1") int currentPage,
            @RequestParam(defaultValue = "3") int perPage) {

        Pageable pageable = PageRequest.of(currentPage - 1, perPage);

        return service.getFiltered(request, pageable);
    }

    @GetMapping("/{id}")
    public CarModel details(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCar(
            @Valid @RequestBody CarCreateModel model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        return ResponseEntity.ok(service.handleRequest(model, CarRequestMode.CREATE));
    }

    @PutMapping("/edit")
    public ResponseEntity<?> editCar(
            @Valid @RequestBody CarCreateModel model, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        return ResponseEntity.ok(service.handleRequest(model, CarRequestMode.EDIT));
    }
}
