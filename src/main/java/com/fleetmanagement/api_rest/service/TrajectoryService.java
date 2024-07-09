package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.dto.LatestTrajectoriesDTO;
import com.fleetmanagement.api_rest.dto.TrajectoryDTO;
import com.fleetmanagement.api_rest.exception.NotFoundException;
import com.fleetmanagement.api_rest.mapper.LatestTrajectoriesDTOMapper;
import com.fleetmanagement.api_rest.mapper.TrajectoryDTOMapper;
import com.fleetmanagement.api_rest.repository.TrajectoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor

@Service
public class  TrajectoryService {
    @Autowired
    private final TrajectoryRepository trajectoryRepository;
    private final TrajectoryDTOMapper trajectoryDTOMapper;
    private final LatestTrajectoriesDTOMapper latestTrajectoriesDTOMapper;

    public List<TrajectoryDTO> getTrajectories(Integer taxi, String date){
        if(trajectoryRepository.findByTaxi(taxi, date).isEmpty()){
            throw new NotFoundException("Register not found");
        }
        return trajectoryRepository.findByTaxi(taxi, date)
                .stream()
                .map(trajectoryDTOMapper)
                .collect(Collectors.toList());
    }

    public List<LatestTrajectoriesDTO> getLatestTrajectories(){
        return trajectoryRepository.findLatestTrajectories()
                .stream()
                .map(latestTrajectoriesDTOMapper)
                .collect(Collectors.toList());

    }
}
