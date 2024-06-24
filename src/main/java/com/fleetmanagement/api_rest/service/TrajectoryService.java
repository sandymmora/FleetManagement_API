package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.model.TrajectoryModel;
import com.fleetmanagement.api_rest.repository.TrajectoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TrajectoryService {
    @Autowired
    TrajectoryRepository trajectoryRepository;

    public List<TrajectoryModel> getTrajectories(Integer taxi, String date){
        return trajectoryRepository.findByTaxi(taxi, date);
    }
}
