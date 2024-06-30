package com.fleetmanagement.api_rest.mapper;

import com.fleetmanagement.api_rest.dto.TrajectoryDTO;
import com.fleetmanagement.api_rest.model.TrajectoryModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class TrajectoryDTOMapper implements Function<TrajectoryModel, TrajectoryDTO> {
    @Override
    public TrajectoryDTO apply(TrajectoryModel trajectory){
        return new TrajectoryDTO(
                trajectory.getId(),
                trajectory.getTaxi().getPlate(),
                trajectory.getTaxi().getId(),
                trajectory.getDate(),
                trajectory.getLongitude(),
                trajectory.getLatitude()
        );
    }
}
