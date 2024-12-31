package org.nkp.autocatalog.entities.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CarFeatureKey implements Serializable {
    @Column(name = "car_id")
    Long carId;

    @Column(name = "feature_id")
    Long featureId;

    public CarFeatureKey() {
    }

    public CarFeatureKey(Long carId, Long featureId) {
        this.carId = carId;
        this.featureId = featureId;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Long getFeatureId() {
        return featureId;
    }

    public void setFeatureId(Long featureId) {
        this.featureId = featureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || getClass() != o.getClass())
            return false;

        var that = (CarFeatureKey) o;

        return carId.equals(that.carId) && featureId.equals(that.featureId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, featureId);
    }
}
