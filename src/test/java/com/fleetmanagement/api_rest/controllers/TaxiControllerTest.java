package com.fleetmanagement.api_rest.controllers;

import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.service.TaxiService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class TaxiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxiService taxiService;

    @BeforeEach
    void setUp(){
        List<TaxiModel> taxis = new ArrayList<>();
        taxis.add(new TaxiModel(1,"HUS-4578"));
        taxis.add(new TaxiModel(2,"WHU-1245"));
        when(taxiService.getTaxis("HU", 0,10)).thenReturn(taxis);
    }

    @Test
    public void getTaxis() throws Exception {
        mockMvc.perform(get("/taxis")
                .param("plate", "HU"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }


}
