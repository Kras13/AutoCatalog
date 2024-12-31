package org.nkp.autocatalog.services;

import jakarta.persistence.EntityNotFoundException;
import org.nkp.autocatalog.entities.Category;
import org.nkp.autocatalog.entities.Fuel;
import org.nkp.autocatalog.models.categories.CategoryCreateModel;
import org.nkp.autocatalog.models.categories.CategoryModel;
import org.nkp.autocatalog.models.fuels.FuelCreateModel;
import org.nkp.autocatalog.models.fuels.FuelModel;
import org.nkp.autocatalog.repositories.FuelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService {
    private final FuelRepository fuelRepository;

    public FuelService(FuelRepository fuelRepository) {
        this.fuelRepository = fuelRepository;
    }

    public FuelModel create(FuelCreateModel model) {
        if (fuelRepository.findByName(model.getName()).isPresent()) {
            throw new EntityNotFoundException("Fuel with such name already exists");
        }

        var fuel = new Fuel(model.getName());
        var savedFuel = fuelRepository.save(fuel);

        return new FuelModel(savedFuel.getId(), savedFuel.getName());
    }

    public List<FuelModel> getAll() {
        return fuelRepository
                .findAll()
                .stream()
                .map(c -> new FuelModel(c.getId(), c.getName()))
                .toList();
    }
}
