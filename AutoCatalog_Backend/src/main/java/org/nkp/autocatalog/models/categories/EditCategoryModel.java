package org.nkp.autocatalog.models.categories;

import jakarta.validation.constraints.NotBlank;

public class EditCategoryModel {
    @NotBlank(message = "Id is required!")
    private Long id;

    @NotBlank(message = "Name is required!")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
