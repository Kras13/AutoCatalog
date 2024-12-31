package org.nkp.autocatalog.controllers;

import jakarta.validation.Valid;
import org.nkp.autocatalog.models.cars.CarCreateModel;
import org.nkp.autocatalog.models.cars.CarModel;
import org.nkp.autocatalog.models.cars.CarRequest;
import org.nkp.autocatalog.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/car")
public class CarController {
    private final CarService service;

    public CarController(CarService service) {
        this.service = service;
    }

    @GetMapping("/fetch")
    public List<CarModel> getAllCars() {
        return service.getAll();
    }

    @GetMapping("/filter")
    public List<CarModel> filterCars(CarRequest request) {
        return service.getAll();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCar(
            @Valid @RequestBody CarCreateModel model, BindingResult bindingResult) throws ParseException {

        if (bindingResult.hasErrors()) {
            return BaseController.FormatBadRequest(bindingResult);
        }

        return ResponseEntity.ok(service.create(model));
    }
}
