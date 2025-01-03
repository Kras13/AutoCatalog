package org.nkp.autocatalog.controllers;

import org.nkp.autocatalog.models.cars.CarFetchResponse;
import org.nkp.autocatalog.services.CarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final CarService service;

    public UserController(CarService service) {
        this.service = service;
    }

    @GetMapping("/cars")
    public List<CarFetchResponse> getAllCars() {
        return service.getForUser();
    }
}
