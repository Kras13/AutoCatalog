package org.nkp.autocatalog.services;

import org.nkp.autocatalog.entities.Brand;
import org.nkp.autocatalog.exceptions.EntityAlreadyExistsException;
import org.nkp.autocatalog.models.brands.BrandCreateModel;
import org.nkp.autocatalog.models.brands.BrandModel;
import org.nkp.autocatalog.repositories.BrandRepository;
import org.nkp.autocatalog.services.contracts.BrandService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    public BrandModel create(BrandCreateModel model) {
        if (brandRepository.findByName(model.getName()).isPresent()) {
            throw new EntityAlreadyExistsException("Brand with such name already exists");
        }

        var savedBrand = brandRepository.save(new Brand(model.getName(), new HashSet<>()));

        return new BrandModel(savedBrand.getId(), savedBrand.getName());
    }

    public List<BrandModel> getAll() {
        return brandRepository
                .findAll()
                .stream()
                .map(c -> new BrandModel(c.getId(), c.getName()))
                .toList();
    }
}
