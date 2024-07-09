package com.fleetmanagement.api_rest.controllers;

import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.model.TrajectoryModel;
import com.fleetmanagement.api_rest.repository.TrajectoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TrajectoryControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrajectoryRepository trajectoryRepository;

    @BeforeEach
    void setUp(){
        List<TrajectoryModel> trajectories = new ArrayList<>();
        trajectories.add(new TrajectoryModel(2,new TaxiModel(4545, "MRN-4856"),new Timestamp(2020),116.30508, 39.96525 ));

        List<TrajectoryModel> latestTrajectories = new ArrayList<>();
        latestTrajectories.add(new TrajectoryModel(5,new TaxiModel(1556,"RFS-4582"),new Timestamp(2020), 116.3043, 39.9622));
        latestTrajectories.add(new TrajectoryModel(2,new TaxiModel(4545, "MRN-4856"),new Timestamp(2020),116.30508, 39.96525 ));
        when(trajectoryRepository.findByTaxi(2, "08-02-2024")).thenReturn(trajectories);
        when(trajectoryRepository.findLatestTrajectories()).thenReturn(latestTrajectories);
    }

    @Test
    public void testTrajectories() throws Exception{
        mockMvc.perform(get("/trajectories")
                .param("taxiId", "2")
                .param("date", "08-02-2024"))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("Should return last trajectories")
    public void testLatestTrajectories() throws Exception{
        mockMvc.perform(get("/trajectories/latest"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
