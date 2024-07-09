package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.dto.LatestTrajectoriesDTO;
import com.fleetmanagement.api_rest.dto.TrajectoryDTO;
import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.model.TrajectoryModel;
import com.fleetmanagement.api_rest.repository.TrajectoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TrajectoriesServiceTest {

    @Autowired
    private TrajectoryService trajectoryService;

    @MockBean
    private TrajectoryRepository trajectoryRepository;

    @BeforeEach
    void setUp(){
        List<TrajectoryModel> trajectories = new ArrayList<>();
        trajectories.add(new TrajectoryModel(2,new TaxiModel(4545, "MRN-4856"),new Timestamp(2020),116.30508, 39.96525 ));

        List<TrajectoryModel> latestTrajectories = new ArrayList<>();
        latestTrajectories.add(new TrajectoryModel(3,new TaxiModel(8784, "MYU-4856"),new Timestamp(2020),116.30508, 39.96525 ));
        latestTrajectories.add(new TrajectoryModel(5,new TaxiModel(1556,"RFS-4582"),new Timestamp(2020), 116.3043, 39.9622));

        when(trajectoryRepository.findByTaxi(4545,"08-02-2024")).thenReturn(trajectories);
        when(trajectoryRepository.findLatestTrajectories()).thenReturn(latestTrajectories);
        System.out.println(latestTrajectories.size());
    }

    @Test
    public void getTrajectoriesByPlate(){
        Integer taxiId = 4545;
        String date = "08-02-2024";
        List<TrajectoryDTO> trajectoryDTOFound = trajectoryService.getTrajectories(4545, "08-02-2024");
        TrajectoryDTO trajectory = trajectoryDTOFound.getFirst();
        assertEquals(taxiId, trajectory.getTaxiId());
        assertEquals(date, "08-02-2024");
    }

    @Test
    public  void getLatestTrajectories(){
        List<LatestTrajectoriesDTO> latestTrajectoriesDTO = trajectoryService.getLatestTrajectories();
        System.out.println(latestTrajectoriesDTO);
        assertEquals(2,latestTrajectoriesDTO.size());

    }
}
