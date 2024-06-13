package com.fleetmanagement.api_rest.controller;

import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/taxis")
public class TaxiController {
    @Autowired
    TaxiService taxiService;

    @GetMapping()
    public ArrayList<TaxiModel> getTaxis() {
        return taxiService.getTaxis();
    }

}
