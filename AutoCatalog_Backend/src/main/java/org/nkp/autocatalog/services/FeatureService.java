package org.nkp.autocatalog.services;

import org.nkp.autocatalog.entities.Feature;
import org.nkp.autocatalog.models.features.FeatureCreateModel;
import org.nkp.autocatalog.models.features.FeatureModel;
import org.nkp.autocatalog.repositories.FeatureRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeatureService {
    private final FeatureRepository featureRepository;

    public FeatureService(FeatureRepository featureRepository) {
        this.featureRepository = featureRepository;
    }

    public List<FeatureModel> getAll() {
        return featureRepository
                .findAll()
                .stream()
                .map(c -> new FeatureModel(c.getId(), c.getName(), c.getDescription()))
                .toList();
    }

    public FeatureModel create(FeatureCreateModel model) {
        if (featureRepository.findByName(model.getName()).isPresent()) {
            throw new IllegalArgumentException("Feature with such name already exists");
        }

        var feature = new Feature(model.getName(), model.getDescription());
        var savedFeature = featureRepository.save(feature);

        return new FeatureModel(savedFeature.getId(), savedFeature.getName(), savedFeature.getDescription());
    }
}
