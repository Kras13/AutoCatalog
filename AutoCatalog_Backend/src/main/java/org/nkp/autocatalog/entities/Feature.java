package org.nkp.autocatalog.entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "features")
public class Feature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "feature")
    private Set<CarFeature> featureCars;

    public Feature() {
    }

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<CarFeature> getFeatureCars() {
        return featureCars;
    }

    public void setFeatureCars(Set<CarFeature> featureCars) {
        this.featureCars = featureCars;
    }
}
