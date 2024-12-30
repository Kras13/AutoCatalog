package org.nkp.autocatalog.models.features;

import jakarta.validation.constraints.NotBlank;

public class FeatureCreateModel {
    @NotBlank(message = "Name is required!")
    private String name;

    @NotBlank(message = "Description is required!")
    private String description;

    public FeatureCreateModel() {
    }

    public FeatureCreateModel(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
