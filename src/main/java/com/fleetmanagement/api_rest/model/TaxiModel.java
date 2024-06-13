package com.fleetmanagement.api_rest.model;

import jakarta.persistence.*;

@Entity
@Table(name = "taxis")
public class TaxiModel {
    @Id
    private Integer id;
    private String plate;

    public TaxiModel(Integer id, String plate) {
        this.id = id;
        this.plate = plate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}

