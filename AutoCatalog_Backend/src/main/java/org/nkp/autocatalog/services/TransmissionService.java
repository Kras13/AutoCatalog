package org.nkp.autocatalog.services;

import org.nkp.autocatalog.entities.Transmission;
import org.nkp.autocatalog.models.transmissions.TransmissionCreateModel;
import org.nkp.autocatalog.models.transmissions.TransmissionModel;
import org.nkp.autocatalog.repositories.TransmissionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransmissionService {
    private final TransmissionRepository transmissionRepository;

    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public TransmissionModel create(TransmissionCreateModel model) {
        if (transmissionRepository.findByName(model.getName()).isPresent()) {
            throw new IllegalArgumentException("Transmission with such name already exists");
        }

        var fuel = new Transmission(model.getName());
        var savedFuel = transmissionRepository.save(fuel);

        return new TransmissionModel(savedFuel.getId(), savedFuel.getName());
    }

    public Boolean existsByName(String name) {
        return transmissionRepository.findByName(name).isPresent();
    }

    public List<TransmissionModel> getAll() {
        return transmissionRepository
                .findAll()
                .stream()
                .map(c -> new TransmissionModel(c.getId(), c.getName()))
                .toList();
    }
}
