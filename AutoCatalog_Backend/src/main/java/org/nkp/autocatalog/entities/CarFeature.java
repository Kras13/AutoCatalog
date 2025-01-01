package org.nkp.autocatalog.entities;

import jakarta.persistence.*;
import org.nkp.autocatalog.entities.keys.CarFeatureKey;

@Entity()
@Table(name = "cars_features")
public class CarFeature {
    @EmbeddedId
    private CarFeatureKey id;

    @ManyToOne
    @MapsId("carId")
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @MapsId("featureId")
    @JoinColumn(name = "feature_id")
    private Feature feature;

    public CarFeature() {
    }

    public CarFeature(Car car, Feature feature) {
        this.id = new CarFeatureKey(car.getId(), feature.getId());
        this.car = car;
        this.feature = feature;
    }

    public CarFeatureKey getId() {
        return id;
    }

    public void setId(CarFeatureKey id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
