package org.nkp.autocatalog.models.transmissions;

import jakarta.validation.constraints.NotBlank;

public class TransmissionCreateModel {
    @NotBlank(message = "Name is required!")
    private String name;

    public TransmissionCreateModel() {
    }

    public TransmissionCreateModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
