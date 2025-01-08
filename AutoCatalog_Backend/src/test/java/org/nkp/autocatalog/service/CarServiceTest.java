package org.nkp.autocatalog.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.nkp.autocatalog.entities.*;
import org.nkp.autocatalog.models.cars.CarFilterRequest;
import org.nkp.autocatalog.repositories.CarRepository;
import org.nkp.autocatalog.services.CarService;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {
    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    public void CarService_FetchFiltered_ReturnCarFilterResponse() {
        var request = new CarFilterRequest();
        var pageable = PageRequest.of(1, 1);
        var page = new PageImpl<Car>(
                List.of(new Car(
                        "demo",
                        "demo",
                        12.00,
                        null,
                        null,
                        null,
                        new Model("demo", new Brand("demo", new HashSet<>())),
                        new Category("demo"),
                        new Fuel("demo"),
                        new Transmission("demo"),
                        new User(
                                "demo", "demo", "demo",
                                "demo", "demo", Role.USER)
                )));

        request.setBrandId(1L);
        request.setModelId(1L);
        request.setTransmissionId(1L);
        request.setFuelId(1L);
        request.setCategoryId(1L);
        request.setFeatures(new ArrayList<Long>());
        request.setFromDate("2001-12-03");
        request.setUntilDate("2001-12-03");

        Mockito.when(carRepository.filterCarPages(
                pageable,
                request.getBrandId(),
                request.getModelId(),
                request.getTransmissionId(),
                request.getFuelId(),
                request.getCategoryId(),
                request.getFeatures(),
                java.sql.Date.valueOf(request.getFromDate()),
                java.sql.Date.valueOf(request.getUntilDate()))).thenReturn(page);

        var filteredCars = carService.getFiltered(request, pageable);

        Assertions.assertThat(filteredCars).isNotNull();
    }
}
