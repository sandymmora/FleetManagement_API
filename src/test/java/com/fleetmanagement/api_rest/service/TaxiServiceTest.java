package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.repository.TaxiRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class TaxiServiceTest {

    @Autowired
    private TaxiService taxiService;

    @MockBean
    private TaxiRepository taxiRepository;

    @BeforeEach
    void setUp(){
        List<TaxiModel> taxi = new ArrayList<>();
        taxi.add(new TaxiModel(3,"STU-6524"));
        Pageable pageable = PageRequest.of(0,1);
        Mockito.when(taxiRepository.findByPlateContaining("STU-6524",pageable)).thenReturn(taxi);
    }

    @Test
    @DisplayName("Should return taxis by plate containing")
    public void getTaxis() {
        String plate = "STU-6524";
        List<TaxiModel> taxisFound = taxiService.getTaxis("STU-6524",0,1);
        TaxiModel taxisFoundFirst = taxisFound.getFirst();
        assertEquals(plate, taxisFoundFirst.getPlate());
    }

}