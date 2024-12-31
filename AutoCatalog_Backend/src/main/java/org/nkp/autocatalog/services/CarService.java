package org.nkp.autocatalog.services;

import jakarta.persistence.EntityNotFoundException;
import org.nkp.autocatalog.entities.*;
import org.nkp.autocatalog.exceptions.DateTimeParseException;
import org.nkp.autocatalog.models.Models.ModelResponse;
import org.nkp.autocatalog.models.brands.BrandModel;
import org.nkp.autocatalog.models.cars.CarCreateModel;
import org.nkp.autocatalog.models.cars.CarModel;
import org.nkp.autocatalog.models.categories.CategoryModel;
import org.nkp.autocatalog.models.fuels.FuelModel;
import org.nkp.autocatalog.models.transmissions.TransmissionModel;
import org.nkp.autocatalog.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class CarService {
    private final ModelRepository modelRepository;
    private final CategoryRepository categoryRepository;
    private final FuelRepository fuelRepository;
    private final TransmissionRepository transmissionRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final FeatureRepository featureRepository;

    public CarService(ModelRepository modelRepository,
                      CategoryRepository categoryRepository,
                      FuelRepository fuelRepository,
                      TransmissionRepository transmissionRepository,
                      UserRepository userRepository,
                      CarRepository carRepository, FeatureRepository featureRepository) {
        this.modelRepository = modelRepository;
        this.categoryRepository = categoryRepository;
        this.fuelRepository = fuelRepository;
        this.transmissionRepository = transmissionRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.featureRepository = featureRepository;
    }

    public CarModel create(CarCreateModel input) throws ParseException {
        var model = modelRepository.findById(input.getModelId());

        if (!model.isPresent())
            throw new EntityNotFoundException("Model with such id does not exist");

        var category = categoryRepository.findById(input.getCategoryId());

        if (!category.isPresent())
            throw new EntityNotFoundException("Category with such id does not exist");

        var fuel = fuelRepository.findById(input.getFuelId());

        if (!fuel.isPresent())
            throw new EntityNotFoundException("Fuel with such id does not exist");

        var transmission = transmissionRepository.findById(input.getTransmissionId());

        if (!transmission.isPresent())
            throw new EntityNotFoundException("Transmission with such id does not exist");

        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (UserDetails)auth.getPrincipal();
        var user = userRepository.findByEmail(userDetails.getUsername());

        if (!user.isPresent()) // todo not needed
            throw new EntityNotFoundException("User with such id does not exist");

        Date dateManufactured;

        try {
            dateManufactured = new SimpleDateFormat("dd/MM/yyyy").parse(input.getDateManufactured());
        }
        catch (ParseException e) {
            throw new DateTimeParseException("Provided date is not in 'dd/MM/yyyy' format");
        }

        var features = featureRepository.findAllById(input.getFeatures());

        if (features.size() != input.getFeatures().size())
            throw new EntityNotFoundException("Feature entity not found");

        var car = new Car(
                input.getTitle(),
                input.getDescription(),
                input.getPrice(),
                dateManufactured,
                model.get(),
                category.get(),
                fuel.get(),
                transmission.get(),
                user.get());

        car.setCarFeatures(features.stream().map(
                e -> new CarFeature(car, e)).collect(Collectors.toSet()));

        var savedCar = carRepository.save(car);
        var savedModel = savedCar.getModel();

        return new CarModel(
                savedCar.getId(),
                new ModelResponse(
                        savedModel.getId(),
                        savedModel.getName(),
                        new BrandModel(savedModel.getBrand().getId(), savedModel.getBrand().getName())),
                savedCar.getTitle(),
                savedCar.getDescription(),
                savedCar.getPrice(),
                savedCar.getDateManufactured(),
                new CategoryModel(category.get().getId(), category.get().getName()),
                new FuelModel(fuel.get().getId(), fuel.get().getName()),
                new TransmissionModel(transmission.get().getId(), transmission.get().getName()));
    }
}
