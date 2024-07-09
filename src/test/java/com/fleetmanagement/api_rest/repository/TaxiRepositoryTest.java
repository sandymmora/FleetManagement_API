package com.fleetmanagement.api_rest.repository;

import com.fleetmanagement.api_rest.model.TaxiModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TaxiRepositoryTest {

    @Autowired
    TaxiRepository taxiRepository;

    @BeforeEach
    void setUp() {
        TaxiModel taxi = new TaxiModel(1,"MDJ-1248");
        TaxiModel taxi2 = new TaxiModel(2,"ZHU-2012");
        taxiRepository.save(taxi);
        taxiRepository.save(taxi2);
    }

    @Test
    public void findTaxiByPlateContainingFound(){
        Pageable pageable = PageRequest.of(0,1);
        List<TaxiModel> taxiModels = taxiRepository.findByPlateContaining("MDJ-1248",pageable);
        assertThat(taxiModels).isNotEmpty();
        assertThat(taxiModels).hasSize(1);
        TaxiModel taxiFound = taxiModels.getFirst();
        assertThat(taxiFound.getPlate()).isEqualTo("MDJ-1248");
    }
}