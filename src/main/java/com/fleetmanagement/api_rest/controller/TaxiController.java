package com.fleetmanagement.api_rest.controller;

import com.fleetmanagement.api_rest.exception.BadRequestException;
import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/taxis")
public class TaxiController {
        
    @Autowired
    TaxiService taxiService;

    @GetMapping()
    public List<TaxiModel> getTaxis(@RequestParam(required = false, defaultValue = "") String plate,
                                    @RequestParam(defaultValue = "0") Integer page,
                                    @RequestParam(defaultValue = "10") Integer limit) {
        return taxiService.getTaxis(plate,page,limit);
    }
}
