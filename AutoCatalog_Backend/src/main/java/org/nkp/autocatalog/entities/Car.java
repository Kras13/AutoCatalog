package org.nkp.autocatalog.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "date_manufactured")
    @Temporal(TemporalType.DATE)
    private Date dateManufactured;

    @Column(name = "kilometers")
    private Long kilometers;

    @Column(name = "image", columnDefinition = "TEXT")
    @Lob
    private String image;

    @ManyToOne
    @JoinColumn(name = "model_id")
    @JsonBackReference
    private Model model;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "fuel_id")
    @JsonBackReference
    private Fuel fuel;

    @ManyToOne
    @JoinColumn(name = "transmission_id")
    @JsonBackReference
    private Transmission transmission;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @OneToMany(mappedBy = "car")
    private Set<CarFeature> carFeatures;

    public Car() {
    }

    public Car(
            String title,
            String description,
            Double price,
            Date dateManufactured,
            Long kilometers,
            String image,
            Model model,
            Category category,
            Fuel fuel,
            Transmission transmission,
            User user) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.dateManufactured = dateManufactured;
        this.kilometers = kilometers;
        this.image = image;
        this.model = model;
        this.category = category;
        this.fuel = fuel;
        this.transmission = transmission;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDateManufactured() {
        return dateManufactured;
    }

    public void setDateManufactured(Date dateManufactured) {
        this.dateManufactured = dateManufactured;
    }

    public Set<CarFeature> getCarFeatures() {
        return carFeatures;
    }

    public void setCarFeatures(Set<CarFeature> carFeatures) {
        this.carFeatures = carFeatures;
    }

    public Long getKilometers() {
        return kilometers;
    }

    public void setKilometers(Long kilometers) {
        this.kilometers = kilometers;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
