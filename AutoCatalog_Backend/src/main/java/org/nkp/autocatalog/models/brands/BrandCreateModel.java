package org.nkp.autocatalog.models.brands;

import jakarta.validation.constraints.NotBlank;

public class BrandCreateModel {
    @NotBlank(message = "Name is required!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
