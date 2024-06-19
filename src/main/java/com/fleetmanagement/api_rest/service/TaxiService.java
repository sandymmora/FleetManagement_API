package com.fleetmanagement.api_rest.service;

import com.fleetmanagement.api_rest.model.TaxiModel;
import com.fleetmanagement.api_rest.repository.TaxiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TaxiService {
    @Autowired
    private TaxiRepository taxiRepository;


    public Page<TaxiModel> getTaxis(String plate, Integer page, Integer limit){
        Pageable pageable = PageRequest.of(page,limit);
        return taxiRepository.findByPlateContaining(plate, pageable);
    }
}

