package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.dto.TrajectoryDTO;
import com.fleetmanagement.api_rest.dto.TrajectoryDTOMapper;
import com.fleetmanagement.api_rest.model.TrajectoryModel;
import com.fleetmanagement.api_rest.repository.TrajectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrajectoryService {
    @Autowired
    private final TrajectoryRepository trajectoryRepository;
    private final TrajectoryDTOMapper trajectoryDTOMapper;

    public TrajectoryService(TrajectoryRepository trajectoryRepository, TrajectoryDTOMapper trajectoryDTOMapper) {
        this.trajectoryRepository = trajectoryRepository;
        this.trajectoryDTOMapper = trajectoryDTOMapper;
    }

    public List<TrajectoryDTO> getTrajectories(Integer taxi, String date){
        return trajectoryRepository.findByTaxi(taxi, date)
                .stream()
                .map(trajectoryDTOMapper)
                .collect(Collectors.toList());
    }
}
