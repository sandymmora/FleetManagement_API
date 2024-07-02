package com.fleetmanagement.api_rest.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class LatestTrajectoriesDTO {
    private Integer taxiId;
    private String plate;
    private Timestamp date;
    private Double latitude;
    private Double longitude;
}
