package org.nkp.autocatalog.models.cars;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public class CarCreateModel {
    private Long id;
    private Long modelId;

    @NotBlank(message = "Title is required!")
    private String title;

    @NotBlank(message = "Description is required!")
    private String description;

    private double price;

    private String dateManufactured;

    private Long categoryId;
    private Long fuelId;
    private Long transmissionId;
    private List<Long> features;

    public CarCreateModel() {
    }

    public CarCreateModel(
            Long id,
            Long modelId,
            String title,
            String description,
            double price,
            String dateManufactured,
            Long categoryId,
            Long fuelId,
            Long transmissionId, List<Long> features) {
        this.id = id;
        this.modelId = modelId;
        this.title = title;
        this.description = description;
        this.price = price;
        this.dateManufactured = dateManufactured;
        this.categoryId = categoryId;
        this.fuelId = fuelId;
        this.transmissionId = transmissionId;
        this.features = features;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDateManufactured() {
        return dateManufactured;
    }

    public void setDateManufactured(String dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public Long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
    }

    public List<Long> getFeatures() {
        return features;
    }

    public void setFeatures(List<Long> features) {
        this.features = features;
    }
}
