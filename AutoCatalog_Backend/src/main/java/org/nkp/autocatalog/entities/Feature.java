package org.nkp.autocatalog.entities;

import jakarta.persistence.*;

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

    public Feature(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Feature() {
    }
}
