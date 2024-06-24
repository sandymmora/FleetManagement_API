package com.fleetmanagement.api_rest.controller;

import com.fleetmanagement.api_rest.model.TrajectoryModel;
import com.fleetmanagement.api_rest.service.TrajectoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/trajectories")
public class TrajectoryController {

    @Autowired
    TrajectoryService trajectoryService;

    @GetMapping()
    public List<TrajectoryModel> getTrajectories(@RequestParam Integer taxi,
                                                 @RequestParam String date){
        return trajectoryService.getTrajectories(taxi,date);
    }
}
