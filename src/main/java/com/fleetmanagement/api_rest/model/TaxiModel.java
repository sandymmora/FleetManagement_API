package com.fleetmanagement.api_rest.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

@Entity
@Table(name = "taxis")
public class TaxiModel {
    @Id
    private Integer id;
    private String plate;
}

