package com.fleetmanagement.api_rest.model;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

@Entity
@Table(name= "trajectories")
public class TrajectoryModel {
    @Id
    private Integer id;
    @ManyToOne
    @JoinColumn(name="taxi_id")
    private TaxiModel taxi;
    private Timestamp date;
    private Double latitude;
    private Double longitude;
}
