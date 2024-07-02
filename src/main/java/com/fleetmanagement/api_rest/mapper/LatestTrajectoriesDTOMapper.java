package com.fleetmanagement.api_rest.mapper;

import com.fleetmanagement.api_rest.dto.LatestTrajectoriesDTO;
import com.fleetmanagement.api_rest.model.TrajectoryModel;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class LatestTrajectoriesDTOMapper implements Function<TrajectoryModel, LatestTrajectoriesDTO>{
    @Override
    public LatestTrajectoriesDTO apply (TrajectoryModel trajectoryModel){
        return new LatestTrajectoriesDTO(
                trajectoryModel.getTaxi().getId(),
                trajectoryModel.getTaxi().getPlate(),
                trajectoryModel.getDate(),
                trajectoryModel.getLatitude(),
                trajectoryModel.getLongitude()
        );
    }

}
