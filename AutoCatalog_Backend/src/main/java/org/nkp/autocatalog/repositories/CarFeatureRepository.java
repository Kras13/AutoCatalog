package org.nkp.autocatalog.repositories;

import org.nkp.autocatalog.entities.CarFeature;
import org.nkp.autocatalog.entities.keys.CarFeatureKey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarFeatureRepository extends JpaRepository<CarFeature, CarFeatureKey> {
    void deleteAllByCarId(Long carId);
}
