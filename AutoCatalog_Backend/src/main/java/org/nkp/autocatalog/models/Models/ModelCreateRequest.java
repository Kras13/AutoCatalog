package org.nkp.autocatalog.models.Models;

import jakarta.validation.constraints.NotBlank;

public class ModelCreateRequest {
    @NotBlank(message = "Name is required!")
    private String name;
    private Long brandId;

    public ModelCreateRequest(String name, Long brandId) {
        this.name = name;
        this.brandId = brandId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
