package com.fleetmanagement.api_rest.repository;

import com.fleetmanagement.api_rest.model.TaxiModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxiRepository extends JpaRepository<TaxiModel, Integer> {
    List<TaxiModel> findByPlateContaining(String plate, Pageable pageable);
}
