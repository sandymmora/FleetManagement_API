package com.fleetmanagement.api_rest.controller;

import com.fleetmanagement.api_rest.dto.TrajectoryDTO;
import com.fleetmanagement.api_rest.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/trajectories")
public class TrajectoryController {

    @Autowired
    TrajectoryService trajectoryService;

    @GetMapping()
    public List<TrajectoryDTO> getTrajectories(@RequestParam String taxiId,
                                               @RequestParam String date){
        Integer taxi = Integer.parseInt(taxiId);
        return trajectoryService.getTrajectories(taxi,date);
    }
}
