package org.nkp.autocatalog.models.Models;

import org.nkp.autocatalog.models.brands.BrandModel;

public class ModelResponse {
    private Long id;
    private String name;
    private BrandModel brand;

    public ModelResponse(Long id, String name, BrandModel brand) {
        this.id = id;
        this.name = name;
        this.brand = brand;
    }

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

    public BrandModel getBrand() {
        return brand;
    }

    public void setBrand(BrandModel brand) {
        this.brand = brand;
    }
}
