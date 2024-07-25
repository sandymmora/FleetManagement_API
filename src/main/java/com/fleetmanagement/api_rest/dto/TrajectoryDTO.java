package com.fleetmanagement.api_rest.dto;

import lombok.*;

import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data

public class TrajectoryDTO {
    private Integer id;
    private String plate;
    private Integer taxiId;
    private Timestamp date;
    private Double latitude;
    private Double longitude;
}
