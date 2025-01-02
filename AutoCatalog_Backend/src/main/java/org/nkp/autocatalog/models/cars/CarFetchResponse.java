package org.nkp.autocatalog.models.cars;

public class CarFetchResponse {
    private Long id;
    private String brand;
    private String model;
    private String title;
    private Long yearManufactured;
    private String fuel;
    private Long kilometers;

    public CarFetchResponse() {
    }

    public CarFetchResponse(
            Long id, String brand, String model, String title, Long yearManufactured, String fuel, Long kilometers) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.title = title;
        this.yearManufactured = yearManufactured;
        this.fuel = fuel;
        this.kilometers = kilometers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getYearManufactured() {
        return yearManufactured;
    }

    public void setYearManufactured(Long yearManufactured) {
        this.yearManufactured = yearManufactured;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public void setKilometers(Long kilometers) {
        this.kilometers = kilometers;
    }
}
