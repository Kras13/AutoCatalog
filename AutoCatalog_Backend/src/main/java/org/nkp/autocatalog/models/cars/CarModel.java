package org.nkp.autocatalog.models.cars;

import org.nkp.autocatalog.models.Models.ModelResponse;
import org.nkp.autocatalog.models.brands.BrandModel;
import org.nkp.autocatalog.models.categories.CategoryModel;
import org.nkp.autocatalog.models.features.FeatureModel;
import org.nkp.autocatalog.models.fuels.FuelModel;
import org.nkp.autocatalog.models.transmissions.TransmissionModel;
import org.nkp.autocatalog.models.users.UserModel;

import java.util.Date;
import java.util.List;

public class CarModel {
    private Long id;
    private BrandModel brand;
    private ModelResponse model;
    private String title;
    private String description;
    private Double price;
    private Long kilometers;
    private Date dateManufactured;
    private CategoryModel category;
    private FuelModel fuel;
    private TransmissionModel transmission;
    private UserModel user;
    private List<FeatureModel> features;

    public CarModel() {
    }

    public CarModel(
            Long id,
            BrandModel brand,
            ModelResponse model,
            String title,
            String description,
            Double price,
            Long kilometers, Date dateManufactured,
            CategoryModel category,
            FuelModel fuel,
            TransmissionModel transmission,
            UserModel user,
            List<FeatureModel> features) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.title = title;
        this.description = description;
        this.price = price;
        this.kilometers = kilometers;
        this.dateManufactured = dateManufactured;
        this.category = category;
        this.fuel = fuel;
        this.transmission = transmission;
        this.user = user;
        this.features = features;
    }

    public BrandModel getBrand() {
        return brand;
    }

    public void setBrand(BrandModel brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ModelResponse getModel() {
        return model;
    }

    public void setModel(ModelResponse model) {
        this.model = model;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public void setKilometers(Long kilometers) {
        this.kilometers = kilometers;
    }

    public Date getDateManufactured() {
        return dateManufactured;
    }

    public void setDateManufactured(Date dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public CategoryModel getCategory() {
        return category;
    }

    public void setCategory(CategoryModel category) {
        this.category = category;
    }

    public FuelModel getFuel() {
        return fuel;
    }

    public void setFuel(FuelModel fuel) {
        this.fuel = fuel;
    }

    public TransmissionModel getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionModel transmission) {
        this.transmission = transmission;
    }

    public List<FeatureModel> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureModel> features) {
        this.features = features;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
