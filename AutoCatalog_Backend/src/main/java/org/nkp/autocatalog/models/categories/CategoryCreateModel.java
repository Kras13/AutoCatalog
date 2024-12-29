package org.nkp.autocatalog.models.categories;

import jakarta.validation.constraints.NotBlank;

public class CategoryCreateModel {
    @NotBlank(message = "Name is required!")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
