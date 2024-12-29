package org.nkp.autocatalog.services;

import org.nkp.autocatalog.entities.Brand;
import org.nkp.autocatalog.models.brands.BrandCreateModel;
import org.nkp.autocatalog.models.brands.BrandModel;
import org.nkp.autocatalog.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BrandService {
    private final BrandRepository brandRepository;

    public BrandService(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandModel create(BrandCreateModel model) {
        if (brandRepository.findByName(model.getName()).isPresent()) {
            throw new IllegalArgumentException("Brand with such name already exists");
        }

        var savedBrand = brandRepository.save(new Brand(model.getName(), new HashSet<>()));

        return new BrandModel(savedBrand.getId(), savedBrand.getName());
    }

    public Boolean existsByName(String name) {
        return brandRepository.findByName(name).isPresent();
    }

    public List<BrandModel> getAll() {
        return brandRepository
                .findAll()
                .stream()
                .map(c -> new BrandModel(c.getId(), c.getName()))
                .toList();
    }

}
