package com.fleetmanagement.api_rest.repository;

import com.fleetmanagement.api_rest.model.TaxiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaxiRepository extends JpaRepository<TaxiModel, Integer> {
}
