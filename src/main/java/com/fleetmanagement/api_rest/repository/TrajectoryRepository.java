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
    public List<TrajectoryModel> findByTaxi(@Param("taxi") Integer taxi, @Param("date") String date);
}
