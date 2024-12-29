package org.nkp.autocatalog.models.fuels;

import jakarta.validation.constraints.NotBlank;

public class FuelCreateModel {
    @NotBlank(message = "Name is required!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FuelCreateModel(String name) {
        this.name = name;
    }
}
