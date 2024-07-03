package com.fleetmanagement.api_rest.repository;

import com.fleetmanagement.api_rest.model.TrajectoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrajectoryRepository extends JpaRepository<TrajectoryModel, Integer> {
    @Query(value = "SELECT * FROM trajectories WHERE TO_CHAR(date::date, 'DD-MM-YYYY') = :date AND taxi_id = :taxi", nativeQuery = true)
    List<TrajectoryModel> findByTaxi(@Param("taxi") Integer taxi, @Param("date") String date);

    @Query(value = "WITH latest_trajectories AS(" +
            "SELECT *,ROW_NUMBER() OVER (PARTITION BY taxi_id ORDER BY date desc) AS rn " +
            "FROM trajectories) " +
            "SELECT * FROM latest_trajectories WHERE rn = 1 " +
            "ORDER BY date desc", nativeQuery = true)
    List<TrajectoryModel> findLatestTrajectories();
}

