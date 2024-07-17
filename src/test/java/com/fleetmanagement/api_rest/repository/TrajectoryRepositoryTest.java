package com.fleetmanagement.api_rest.repository;

import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.model.TrajectoryModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatException;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TrajectoryRepositoryTest {

    @Autowired
    TrajectoryRepository trajectoryRepository;

    @Autowired
    TaxiRepository taxiRepository;

    @BeforeEach
    void setUp(){

        TaxiModel taxiModel1 = new TaxiModel(7282, "MKS-2564");
        TaxiModel taxiModel2 = new TaxiModel(4545, "MDR-2558");

        TaxiModel taxi1 = taxiRepository.save(taxiModel1);
        TaxiModel taxi2 = taxiRepository.save(taxiModel2);

        Timestamp date1 = Timestamp.valueOf(
                LocalDateTime.of(2024,3,10, 0,0));
        Timestamp date2 = Timestamp.valueOf(
                LocalDateTime.of(2024,4,5,0,0));
        Timestamp date3 = Timestamp.valueOf(
                LocalDateTime.of(2024,2,8,0,0));

        TrajectoryModel trajectory1 =
                new TrajectoryModel(1,taxi1,date1,116.30508, 39.96525 );
        TrajectoryModel trajectory2 =
                new TrajectoryModel(2,taxi2,date2,145.30508, 18.96525 );
        TrajectoryModel trajectory3 =
                new TrajectoryModel(3,taxi2,date3,145.30508, 18.96525 );
        trajectoryRepository.save(trajectory1);
        trajectoryRepository.save(trajectory2);
        trajectoryRepository.save(trajectory3);
    }

    @Test
    public void findByTaxiFound() {
        List<TrajectoryModel> trajectories = trajectoryRepository.findByTaxi(4545,"08-02-2024");
        assertThat(trajectories).size().isEqualTo(1);
        TrajectoryModel trajectoryFound = trajectories.getFirst();
        assertThat(trajectoryFound.getTaxi().getId()).isEqualTo(4545);
        //assertThat(trajectoryFound.getDate()).isEqualTo("08-02-2024");

    }
    @Test
    public void findLastTrajectories() {
        List<TrajectoryModel> latestTrajectories = trajectoryRepository.findLatestTrajectories();
        assertThat(latestTrajectories).isNotEmpty();
        assertThat(latestTrajectories).size().isEqualTo(2);
    }
}
