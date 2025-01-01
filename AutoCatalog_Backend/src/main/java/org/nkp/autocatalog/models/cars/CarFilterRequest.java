package org.nkp.autocatalog.models.cars;

import java.util.List;

public class CarFilterRequest {
    Long brandId;
    private Long modelId;
    private Long transmissionId;
    private Long fuelId;
    private Long categoryId;
    private List<Long> features;
    private String fromDate;
    private String untilDate;

    public CarFilterRequest() {
    }

    public CarFilterRequest(
            Long brandId,
            Long modelId,
            Long transmissionId,
            Long fuelId,
            Long categoryId,
            List<Long> features,
            String fromDate,
            String untilDate) {
        this.brandId = brandId;
        this.modelId = modelId;
        this.transmissionId = transmissionId;
        this.fuelId = fuelId;
        this.categoryId = categoryId;
        this.features = features;
        this.fromDate = fromDate;
        this.untilDate = untilDate;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public Long getTransmissionId() {
        return transmissionId;
    }

    public void setTransmissionId(Long transmissionId) {
        this.transmissionId = transmissionId;
    }

    public Long getFuelId() {
        return fuelId;
    }

    public void setFuelId(Long fuelId) {
        this.fuelId = fuelId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public List<Long> getFeatures() {
        return features;
    }

    public void setFeatures(List<Long> features) {
        this.features = features;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getUntilDate() {
        return untilDate;
    }

    public void setUntilDate(String untilDate) {
        this.untilDate = untilDate;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
