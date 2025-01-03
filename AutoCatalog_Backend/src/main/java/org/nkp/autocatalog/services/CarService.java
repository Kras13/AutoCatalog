package org.nkp.autocatalog.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.nkp.autocatalog.entities.*;
import org.nkp.autocatalog.exceptions.DateTimeParseException;
import org.nkp.autocatalog.exceptions.UnauthorizedEditException;
import org.nkp.autocatalog.models.Models.ModelResponse;
import org.nkp.autocatalog.models.brands.BrandModel;
import org.nkp.autocatalog.models.cars.*;
import org.nkp.autocatalog.models.categories.CategoryModel;
import org.nkp.autocatalog.models.features.FeatureModel;
import org.nkp.autocatalog.models.fuels.FuelModel;
import org.nkp.autocatalog.models.transmissions.TransmissionModel;
import org.nkp.autocatalog.repositories.*;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class CarService {
    private final ModelRepository modelRepository;
    private final CategoryRepository categoryRepository;
    private final FuelRepository fuelRepository;
    private final TransmissionRepository transmissionRepository;
    private final UserRepository userRepository;
    private final CarRepository carRepository;
    private final FeatureRepository featureRepository;
    private final CarFeatureRepository carFeatureRepository;

    public CarService(ModelRepository modelRepository,
                      CategoryRepository categoryRepository,
                      FuelRepository fuelRepository,
                      TransmissionRepository transmissionRepository,
                      UserRepository userRepository,
                      CarRepository carRepository,
                      FeatureRepository featureRepository,
                      CarFeatureRepository carFeatureRepository) {
        this.modelRepository = modelRepository;
        this.categoryRepository = categoryRepository;
        this.fuelRepository = fuelRepository;
        this.transmissionRepository = transmissionRepository;
        this.userRepository = userRepository;
        this.carRepository = carRepository;
        this.featureRepository = featureRepository;
        this.carFeatureRepository = carFeatureRepository;
    }

    public List<CarFetchResponse> getAll() {
        return carRepository.findAll()
                .stream()
                .map(this::projectToFetchModel)
                .toList();
    }

    public List<CarFetchResponse> getForUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        var userDetails = (UserDetails)auth.getPrincipal();
        var user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(() -> new EntityNotFoundException("User does not exist."));

        return carRepository.findByUser(user)
                .stream()
                .map(this::projectToFetchModel)
                .toList();
    }

    public CarModel getById(Long id) {
        var car = carRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Car with such id was not found"));

        return projectToCarModel(car);
    }

    public CarModel handleRequest(CarCreateModel input, CarRequestMode mode){
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
        java.sql.Date sqlDateManufactured;

        try {
            dateManufactured = new SimpleDateFormat("yyyy-MM-dd").parse(input.getDateManufactured());
            sqlDateManufactured = new java.sql.Date(dateManufactured.getTime());
        }
        catch (ParseException e) {
            throw new DateTimeParseException("Provided date is not in 'yyyy-MM-dd' format");
        }

        var features = featureRepository.findAllById(input.getFeatures());

        if (features.size() != input.getFeatures().size())
            throw new EntityNotFoundException("Feature entity not found");

        if (mode == CarRequestMode.CREATE) {
            var car = new Car(
                    input.getTitle(),
                    input.getDescription(),
                    input.getPrice(),
                    sqlDateManufactured,
                    input.getKilometers(),
                    model.get(),
                    category.get(),
                    fuel.get(),
                    transmission.get(),
                    user.get());

            var savedCar = carRepository.save(car);

            setCarFeatures(savedCar, features);

            return projectToCarModel(savedCar);
        }
        else {
            var car = carRepository.findById(input.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Car with such id was not found"));

            if (!car.getUser().getId().equals(user.get().getId())) {
                throw new UnauthorizedEditException("Unauthorized car edit");
            }

            var editedCar = editCarInternal(
                    car,
                    features,
                    model.get(),
                    category.get(),
                    fuel.get(),
                    transmission.get(),
                    input.getPrice(),
                    input.getTitle(),
                    input.getDescription());

            return projectToCarModel(editedCar);
        }
    }

    public Car editCarInternal(
            Car car,
            List<Feature> features,
            Model model,
            Category category,
            Fuel fuel,
            Transmission transmission,
            double price,
            String title,
            String description) {

        car.setModel(model);
        car.setCategory(category);
        car.setFuel(fuel);
        car.setTransmission(transmission);
        car.setPrice(price);
        car.setTitle(title);
        car.setDescription(description);

        carFeatureRepository.deleteAllByCarId(car.getId());

        setCarFeatures(car, features);

        return carRepository.save(car);
    }

    private void setCarFeatures(Car car, List<Feature> features) {
        var carsFeatures = new HashSet<CarFeature>();

        for (var feature : features) {
            var carFeature = new CarFeature(car, feature);
            var savedCarFeature = carFeatureRepository.save(carFeature);

            carsFeatures.add(savedCarFeature);
        }

        car.setCarFeatures(carsFeatures);
    }

    public CarFilterResponse getFiltered(CarFilterRequest request, Pageable pageable) {
        java.sql.Date sqlFromDate = null;
        java.sql.Date sqlUntilDate = null;

        try {
            if (request.getFromDate() != null) {
                var fromDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getFromDate());

                sqlFromDate = new java.sql.Date(fromDate.getTime());
            }


            if (request.getUntilDate() != null) {
                var untilDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getUntilDate());

                sqlUntilDate = new java.sql.Date(untilDate.getTime());
            }

        }
        catch (ParseException e) {
            throw new DateTimeParseException("Dates must be in 'yyyy-MM-dd' format");
        }

        var cars = carRepository.filterCarPages(
                pageable,
                request.getBrandId(),
                request.getModelId(),
                request.getTransmissionId(),
                request.getFuelId(),
                request.getCategoryId(),
                request.getFeatures(),
                sqlFromDate,
                sqlUntilDate);

        var projectedCars = cars
                .stream()
                .map(this::projectToFetchModel)
                .toList();

        return new CarFilterResponse(projectedCars, cars.getTotalPages(), cars.getTotalElements());
    }

    private CarFetchResponse projectToFetchModel(Car source) {

        Long yearManufactured = null;

        if (source.getDateManufactured() != null) {
            var calendar = new GregorianCalendar();

            calendar.setTime(source.getDateManufactured());

            yearManufactured = (long) calendar.get(Calendar.YEAR);
        }

        return new CarFetchResponse(
                source.getId(),
                source.getModel().getBrand().getName(),
                source.getModel().getName(),
                source.getTitle(),
                source.getPrice(),
                yearManufactured,
                source.getFuel().getName(),
                source.getKilometers());
    }

    private CarModel projectToCarModel(Car source) {
        var carModel = source.getModel();
        var category = source.getCategory();
        var fuel = source.getFuel();
        var transmission = source.getTransmission();
        var features = source.getCarFeatures()
                .stream()
                .map(e -> new FeatureModel(
                        e.getFeature().getId(), e.getFeature().getName(), e.getFeature().getDescription()))
                .toList();

        return new CarModel(
                source.getId(),
                new ModelResponse(
                        carModel.getId(),
                        carModel.getName(),
                        new BrandModel(carModel.getBrand().getId(), carModel.getBrand().getName())),
                source.getTitle(),
                source.getDescription(),
                source.getPrice(),
                source.getDateManufactured(),
                new CategoryModel(category.getId(), category.getName()),
                new FuelModel(fuel.getId(), fuel.getName()),
                new TransmissionModel(transmission.getId(), transmission.getName()),
                features);
    }
}
