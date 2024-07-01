package com.fleetmanagement.api_rest.dto;

import com.fleetmanagement.api_rest.model.TaxiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TrajectoryDTO {
    private Integer id;
    private String plate;
    private Integer taxiId;
    private Timestamp date;
    private Double latitude;
    private Double longitude;
}
