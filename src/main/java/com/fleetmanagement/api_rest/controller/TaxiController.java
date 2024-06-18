package com.fleetmanagement.api_rest.controller;

import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taxis")
public class TaxiController {
        
    @Autowired
    TaxiService taxiService;

    @GetMapping()
    public Page<TaxiModel> getTaxis(@RequestParam(required = false) String plate,
                                   @RequestParam(required = true, defaultValue = "1") Integer page,
                                   @RequestParam(required = true, defaultValue = "10") Integer limit) {
        return taxiService.getTaxis(plate,page,limit);
    }
}
