package org.nkp.autocatalog.services;

import jakarta.persistence.EntityNotFoundException;
import org.nkp.autocatalog.entities.Model;
import org.nkp.autocatalog.models.Models.ModelCreateRequest;
import org.nkp.autocatalog.models.Models.ModelResponse;
import org.nkp.autocatalog.models.brands.BrandModel;
import org.nkp.autocatalog.repositories.BrandRepository;
import org.nkp.autocatalog.repositories.ModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;

    public ModelService(BrandRepository brandRepository, ModelRepository modelRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
    }

    public List<ModelResponse> getForMake(Long brandId) {
        var brand = brandRepository.findById(brandId);

        if (!brand.isPresent())
            throw new EntityNotFoundException("Brand with such id does not exist.");

        return modelRepository.findByBrandId(brandId)
                .stream()
                .map(m -> new ModelResponse(
                        m.getId(),
                        m.getName(),
                        new BrandModel(m.getBrand().getId(), m.getBrand().getName())))
                .toList();
    }

    public ModelResponse create(ModelCreateRequest input) {
        var brand = brandRepository.findById(input.getBrandId());

        if (!brand.isPresent())
            throw new EntityNotFoundException("Brand with such id does not exist.");

        var model = new Model(input.getName(), brand.get());
        var savedModel = modelRepository.save(model);
        var brandModel = savedModel.getBrand();

        return new ModelResponse(
                savedModel.getId(),
                savedModel.getName(),
                new BrandModel(brandModel.getId(), brandModel.getName()));
    }
}
